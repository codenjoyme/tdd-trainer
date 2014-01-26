package com.apofig.tddtrainer.client;

import com.apofig.tddtrainer.model.Solver;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * User: serhiy.zelenin
 * Date: 4/8/13
 * Time: 11:42 PM
 */
public class WebSocketRunner {

//    private static final String SERVER = "ws://tetrisj.jvmhost.net:12270/codenjoy-contest/ws";
    private static final String SERVER = "ws://127.0.0.1:8080/tdd-trainer/ws";
    private static String USER_NAME = "apofig";

    private WebSocket.Connection connection;
    private Solver solver;
    private WebSocketClientFactory factory;

    public WebSocketRunner(Solver solver) {
        this.solver = solver;
    }

    public static void main(String[] args) throws Exception {
        run(SERVER, USER_NAME);
    }

    private static void run(String server, String userName) throws Exception {
        final WebSocketRunner client = new WebSocketRunner(new YourSolver());
        client.start(server, userName);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    client.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stop() throws Exception {
        connection.close();
        factory.stop();
    }

    private void start(String server, String userName) throws Exception {
        factory = new WebSocketClientFactory();
        factory.start();

        org.eclipse.jetty.websocket.WebSocketClient client = factory.newWebSocketClient();
        connection = client.open(new URI(server + "?user=" + userName), new WebSocket.OnTextMessage() {
            public void onOpen(Connection connection) {
                System.out.println("Opened");
            }

            public void onClose(int closeCode, String message) {
                System.out.println("Closed");
            }

            public void onMessage(String data) {
                String answer = solver.solve(data);
                System.out.printf("%s=%s\n", data, answer);
                try {
                    connection.sendMessage(answer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).get(5000, TimeUnit.MILLISECONDS);
    }
}
