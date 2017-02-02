package com;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Scanner;

@ServerEndpoint("/chat")

public class CuctomerServerSocket {

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("Connected ... ");
    }

    @OnMessage
    public String onMessage(Session session, String msg) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Server :: message " + msg);
        StartServer.msgFromClient=msg;
        if ("exit".equals(msg))
            session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "exit"));
        return "Server : " + scanner.nextLine();
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

        System.out.println("Server disconnected " + closeReason.getReasonPhrase());
    }
}
