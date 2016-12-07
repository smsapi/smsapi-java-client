package pl.smsapi.api.authenticationStrategy;

public class OAuthAuthentication implements AuthenticationStrategy {
    private String token;

    public OAuthAuthentication(String token) {
        this.token = token;
    }

    @Override
    public String getAuthenticationHeader() {
        return "Bearer " + token;
    }
}
