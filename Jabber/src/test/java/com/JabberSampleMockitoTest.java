package com;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JabberSampleMockitoTest {

    @Mock
    private AbstractXMPPConnection abstractXMPPConnection;

    @Mock
    private Jabber jabber;


    @Test
    public void verifyTest () throws Exception {
        AbstractXMPPConnection connection = jabber.connection("admin", "admin");
        Assert.assertNull(connection);
        verify(jabber, times(1)).connection(anyString(), anyString());
        jabber.addUser("test", "test",connection);
        jabber.addUser("man", "man",connection);
        verify(jabber,times(2)).addUser(anyString(), anyString(), Matchers.<AbstractXMPPConnection>anyObject());
        jabber.removeUser("test", "test",connection);
        jabber.removeUser("man", "man",connection);
        verify(jabber,times(2)).removeUser(anyString(), anyString(), Matchers.<AbstractXMPPConnection>anyObject());
        jabber.chat(connection, "test");
        jabber.chat(connection, "man");
        verify(jabber,times(2)).chat(Matchers.<AbstractXMPPConnection>anyObject(),anyString());
        jabber.createInstantChatRoom(connection, "test");
        jabber.createInstantChatRoom(connection, "man");
        verify(jabber,times(2)).createInstantChatRoom(Matchers.<AbstractXMPPConnection>anyObject(),anyString());
    }

}
