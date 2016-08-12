package com.apofig.tddtrainer.client;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
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

//    private static final String SERVER = "ws://tetrisj.jvmhost.net:12270/tdd-trainer/ws";
    private static final String SERVER = "ws://127.0.0.1:8080/tdd-trainer/ws";
    private static String USER_NAME = "userName";

    private WebSocket.Connection connection;
    private Solver solver;
    private WebSocketClientFactory factory;

    public WebSocketRunner(Solver solver) {
        this.solver = solver;
    }

    public static void main(String[] args) throws Exception {
		String userName = USER_NAME;
		if (args.length == 1 && args[0] != null) {
			userName = args[0];
		}
		run(SERVER, userName);
    }

    private static void run(String server, String userName) throws Exception {
		System.out.printf("Connecting '%s' to the '%s'\n", userName, server);
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

        WebSocketClient client = factory.newWebSocketClient();
        connection = client.open(new URI(server + "?user=" + userName), new WebSocket.OnTextMessage() {
            public void onOpen(Connection connection) {
                System.out.println("Connected successfully!");
            }

            public void onClose(int closeCode, String message) {
                System.out.println("Connection closed!");
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
