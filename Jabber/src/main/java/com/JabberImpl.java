package com;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class JabberImpl implements Jabber {
    public static void main(String[] args) throws Exception {

        JabberImpl jabber = new JabberImpl();
        AbstractXMPPConnection connection = jabber.connection("test", "test");
        connection.login();
        //jabber.chat(connection, "user");

        jabber.createInstantChatRoom(connection, "user");


    }

    public AbstractXMPPConnection connection(String name, String password) throws IOException, XMPPException, SmackException {

        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
        configBuilder.setUsernameAndPassword(name, password);
        configBuilder.setPort(5222);
        configBuilder.setHost("localhost");
        configBuilder.setServiceName("conference.localhost");
        configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);

        AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());

        return connection.connect();
    }

    public void addUser(String name, String password, AbstractXMPPConnection connection) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        AccountManager accountManager = AccountManager.getInstance(connection);
        accountManager.createAccount(name, password);
    }

    public void removeUser(String name, String password, AbstractXMPPConnection connection) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException {
        AccountManager accountManager = AccountManager.getInstance(connection);
        accountManager.deleteAccount();
    }

    public void chat(AbstractXMPPConnection connection, String user) throws SmackException.NotConnectedException {
        ChatManager chatmanager = ChatManager.getInstanceFor(connection);
        Chat newChat = chatmanager.createChat(user + "@localhost", new ChatMessageListener() {
            public void processMessage(Chat chat, Message message) {
                System.out.println(chat.getParticipant() + " -> " + message.getBody());
            }
        });
        Scanner console = new Scanner(System.in);
        while (connection.isConnected()) {
            newChat.sendMessage(console.nextLine());
        }
    }

        public void createInstantChatRoom(AbstractXMPPConnection connection, String name) throws Exception {

        MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(connection);
        List<String> services = manager.getServiceNames();

        if (services.isEmpty()) {
            System.out.println("Service not found");
        }

        System.out.println(services.get(0));

        MultiUserChat muc = manager.getMultiUserChat("newroom@" + services.get(0));
        muc.join(name);
        muc.sendMessage("messege");

        Thread.sleep(1000);
        muc.leave();
    }
}
