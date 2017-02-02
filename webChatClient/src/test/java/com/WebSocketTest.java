package com;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.server.Server;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.util.concurrent.CountDownLatch;


public class WebSocketTest {

    @Test
    public void testConnectToServerEndpoint() throws Exception {

        Server server = new Server("localhost", 9191, "/websockets", CuctomerServerSocket.class);
        server.start();
        StartServer startServer = new StartServer();
        CountDownLatch countDownLatch = new CountDownLatch(1);
//        new CustomClientSocket();
        ClientManager clientManager = new ClientManager();
        clientManager.connectToServer(CustomClientSocket.class,
                new URI("ws://localhost:9191/websockets/chat"));
        String msgFromClient = StartServer.msgFromClient;
        Assert.assertEquals("test",msgFromClient);
        server.stop();
    }
}
