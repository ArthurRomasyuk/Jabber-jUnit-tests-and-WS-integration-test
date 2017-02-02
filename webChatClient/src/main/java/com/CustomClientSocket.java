package com;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by BGClassTeacher on 17.11.2016.
 */
@ClientEndpoint
public class CustomClientSocket {

    @OnOpen
    public void onConnect(Session session) throws IOException {
        System.out.println("client:: connect is open");
        session.getBasicRemote().sendText("test");
    }

    @OnMessage
    public String onMessage(Session session, String msg) {
        System.out.println("client:: message");
        return "test";
    }


    @OnClose
    public void close(Session session, CloseReason closeReason) {
        System.out.println("disconnect:::reason ->" + closeReason.getReasonPhrase());
    }

    public static void main(String[] args) throws URISyntaxException, DeploymentException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
//        new CustomClientSocket();
        ClientManager clientManager = new ClientManager();
        clientManager.connectToServer(CustomClientSocket.class,
                new URI("ws://localhost:9090/websockets/chat"));
        countDownLatch.await();
    }

}
