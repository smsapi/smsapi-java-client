package pl.smsapi.api.authenticationStrategy;

import org.apache.commons.codec.binary.Base64;

@Deprecated
public class BasicAuthenticationStrategy implements AuthenticationStrategy {
    private String username;
    private String password;

    public BasicAuthenticationStrategy(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getAuthenticationHeader() {
        String authStr = this.username + ":" + this.password;
        authStr = Base64.encodeBase64String(authStr.getBytes());

        return "Basic " + authStr;
    }
}
