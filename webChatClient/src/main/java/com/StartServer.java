package com;

import org.glassfish.tyrus.server.Server;

public class StartServer {
    public static String msgFromClient;

    public static void main(String[] args) throws Exception {

        Server server = new Server("localhost", 9191, "/websockets", CuctomerServerSocket.class);
        server.start();
        Thread.sleep(10000000);
        server.stop();
    }

}



