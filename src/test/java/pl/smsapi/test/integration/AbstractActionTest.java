package pl.smsapi.test.integration;

import org.junit.Test;
import pl.smsapi.BasicAuthClient;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.authenticationStrategy.BasicAuthenticationStrategy;
import pl.smsapi.api.authenticationStrategy.BearerAuthenticationStrategy;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ActionMock;
import pl.smsapi.test.doubles.ProxyMock;

import static org.junit.Assert.assertEquals;

public class AbstractActionTest {
    @Test
    public void testItShouldAuthenticateUsingBasicStrategyWhenUsingBasicAuthClient() throws SmsapiException {
        BasicAuthClient client = new BasicAuthClient("<username>", "<password>");
        ProxyMock proxy = new ProxyMock();

        ActionMock action = new ActionMock();
        action.client(client);
        action.proxy(proxy);
        action.execute();

        assertEquals(BasicAuthenticationStrategy.class, proxy.lastUsedAuthenticationStrategy.getClass());
    }

    @Test
    public void testItShouldAuthenticateUsingBearerStrategyWhenUsingOAuthClient() throws SmsapiException {
        OAuthClient client = new OAuthClient("<token>");
        ProxyMock proxy = new ProxyMock();

        ActionMock action = new ActionMock();
        action.client(client);
        action.proxy(proxy);
        action.execute();

        assertEquals(BearerAuthenticationStrategy.class, proxy.lastUsedAuthenticationStrategy.getClass());
    }
}
