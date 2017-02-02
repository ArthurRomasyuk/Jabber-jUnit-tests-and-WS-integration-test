package com;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;

public interface Jabber {

    AbstractXMPPConnection connection(String name, String password) throws IOException, XMPPException, SmackException;

    void addUser(String name, String password, AbstractXMPPConnection connection) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException;

    void removeUser(String name, String password, AbstractXMPPConnection connection) throws SmackException.NotConnectedException, XMPPException.XMPPErrorException, SmackException.NoResponseException;

    void chat(AbstractXMPPConnection connection, String user) throws SmackException.NotConnectedException;

    void createInstantChatRoom(final AbstractXMPPConnection connection, String name) throws Exception;
}
