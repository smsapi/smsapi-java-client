package pl.smsapi;

import pl.smsapi.api.authenticationStrategy.BasicAuthenticationStrategy;

@Deprecated
public class BasicAuthClient implements Client {
    private final String username;
    private final String password;

    public BasicAuthClient(String username, String password) {
        assert username != null && !username.isEmpty() : "Username is empty";
        assert password != null && !password.isEmpty() : "Password is empty";
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public BasicAuthenticationStrategy getAuthenticationStrategy() {
        return new BasicAuthenticationStrategy(username, password);
    }
}
