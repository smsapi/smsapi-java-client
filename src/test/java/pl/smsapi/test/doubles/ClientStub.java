package pl.smsapi.test.doubles;

import pl.smsapi.Client;
import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;

public class ClientStub implements Client {
    @Override
    public AuthenticationStrategy getAuthenticationStrategy() {
        return new AuthenticationStrategyStub();
    }

    private static class AuthenticationStrategyStub implements AuthenticationStrategy {
        @Override
        public String getAuthenticationHeader() {
            return null;
        }
    }
}
