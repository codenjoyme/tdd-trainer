package com.codenjoy.dojo.transport.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FakeHttpServer {

    private String response;
//    private final HttpServer server;
    private StringWriter requestWriter = new StringWriter();
    private Server jettyServer = new Server();
    private Map<String, String[]> parameters;
    private int port;
    private ReentrantLock lock = new ReentrantLock();
    private Condition requestProcessed = lock.newCondition();
    private RuntimeException responseException;
    private long waitTime;

    public FakeHttpServer(int port) throws IOException {
        this.port = port;
    }

    public void start() throws Exception {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        jettyServer.addConnector(connector);
        ServletContextHandler root = new ServletContextHandler(jettyServer, "/");
        root.addServlet(new ServletHolder(new HttpServlet(){
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                lock.lock();
                try {
                    if (waitTime > 0) {
                        try {
                            Thread.sleep(waitTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    parameters = req.getParameterMap();
                    if (responseException != null) {
                        throw responseException;
                    }
                    PrintWriter writer = resp.getWriter();
                    writer.print(response);
                    writer.flush();
                    writer.close();
//                    System.out.println("response = " + response);
                    requestProcessed.signal();
                } finally {
                    lock.unlock();
                }
            }
        }), "/*");

        jettyServer.start();

    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void stop() throws Exception {
        jettyServer.stop();
    }

    public String getRequestParameter(String name) {
        return parameters.get(name)[0];
    }

    public void waitForRequest() throws InterruptedException {
        lock.lock();
        try {
            while (parameters == null) {
                requestProcessed.await(1, TimeUnit.SECONDS);
            }
        } finally {
            lock.unlock();
        }

    }

    public void setResponseException(RuntimeException responseException) {
        this.responseException = responseException;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
}
