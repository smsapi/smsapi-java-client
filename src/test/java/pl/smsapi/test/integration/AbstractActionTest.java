package pl.smsapi.test.integration;

import org.junit.Test;
import pl.smsapi.Client;
import pl.smsapi.api.authenticationStrategy.BasicAuthenticationStrategy;
import pl.smsapi.api.authenticationStrategy.BearerAuthenticationStrategy;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ActionMock;
import pl.smsapi.test.doubles.ProxyMock;

import static org.junit.Assert.assertEquals;

public class AbstractActionTest {
    @Test
    public void testItShouldAuthenticateUsingBasicStrategyWhenTokenIsNull() throws SmsapiException {
        Client client = new Client("some username");
        ProxyMock proxy = new ProxyMock();

        ActionMock action = new ActionMock();
        action.client(client);
        action.proxy(proxy);
        action.execute();

        assertEquals(BasicAuthenticationStrategy.class, proxy.lastUsedAuthenticationStrategy.getClass());
    }

    @Test
    public void testItShouldAuthenticateUsingBearerStrategyWhenTokenIsProvided() throws SmsapiException {
        Client client = Client.createFromToken("some token");
        ProxyMock proxy = new ProxyMock();

        ActionMock action = new ActionMock();
        action.client(client);
        action.proxy(proxy);
        action.execute();

        assertEquals(BearerAuthenticationStrategy.class, proxy.lastUsedAuthenticationStrategy.getClass());
    }
}
