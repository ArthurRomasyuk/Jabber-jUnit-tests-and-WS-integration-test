package com;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
public class JabberPowerMockitoTest {

    private Jabber jabber = PowerMockito.mock(Jabber.class);
    private XMPPTCPConnection xmpptcpConnection = PowerMockito.mock(XMPPTCPConnection.class);
    private XMPPError xmppError = PowerMockito.mock(XMPPError.class);
    private SmackException.NoResponseException noResponseException =  PowerMockito.mock(SmackException.NoResponseException.class);


    @Test
    @PrepareForTest(Jabber.class)
    public void checkConnectionFunctionalityTest() throws Exception {
        //you can use directly new XMPPTCPConnection("some","some");/
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        when(jabber.connection(anyString(), anyString())).thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        Assert.assertEquals(xmpptcpConnection, connection);
    }

    @Test(expected = SmackException.NotConnectedException.class)
    public void testAdUserNotConnectedException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new SmackException.NotConnectedException()).when(jabber).addUser(anyString(), anyString(), eq(connection));
        jabber.addUser("test","test", connection);
    }


    @Test(expected = XMPPException.XMPPErrorException.class)
    public void testAdUserXMPPErrorException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new XMPPException.XMPPErrorException(xmppError)).when(jabber).addUser(anyString(), anyString(), eq(connection));
        jabber.addUser("test","test", connection);
    }

    @Test(expected = SmackException.NoResponseException.class)
    public void testAdUserNoResponseException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(noResponseException).when(jabber).addUser(anyString(), anyString(), eq(connection));
        jabber.addUser("test","test", connection);
    }

    @Test(expected = SmackException.NotConnectedException.class)
    public void testRemoveUserNotConnectedException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new SmackException.NotConnectedException()).when(jabber).removeUser(anyString(), anyString(), eq(connection));
        jabber.removeUser("test","test", connection);
    }


    @Test(expected = XMPPException.XMPPErrorException.class)
    public void testRemoveUserXMPPErrorException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new XMPPException.XMPPErrorException(xmppError)).when(jabber).removeUser(anyString(), anyString(), eq(connection));
        jabber.removeUser("test","test", connection);
    }

    @Test(expected = SmackException.NoResponseException.class)
    public void testRemoveUserNoResponseException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(noResponseException).when(jabber).removeUser(anyString(), anyString(), eq(connection));
        jabber.removeUser("test","test", connection);
    }

    @Test(expected = SmackException.NotConnectedException.class)
    public void testChatNotConnectedException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new SmackException.NotConnectedException()).when(jabber).chat(eq(connection), anyString());
        jabber.chat(connection, "test");
    }

    @Test(expected = Exception.class)
    public void testCreateInstantChatRoomNotConnectedException() throws Exception {
        whenNew(XMPPTCPConnection.class).withAnyArguments().thenReturn(xmpptcpConnection);
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        doThrow(new Exception()).when(jabber).createInstantChatRoom(eq(connection), anyString());
        jabber.createInstantChatRoom(connection, "test");
    }



}
