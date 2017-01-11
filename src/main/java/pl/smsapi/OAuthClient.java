package pl.smsapi;

import pl.smsapi.api.authenticationStrategy.BearerAuthenticationStrategy;
import pl.smsapi.exception.ClientException;

public class OAuthClient implements Client {
    private final String token;

    public OAuthClient(String token) throws ClientException {
        assert token != null && !token.isEmpty() : "Token is null";
        this.token = token;
    }

    @Override
    public BearerAuthenticationStrategy getAuthenticationStrategy() {
        return new BearerAuthenticationStrategy(token);
    }
}
