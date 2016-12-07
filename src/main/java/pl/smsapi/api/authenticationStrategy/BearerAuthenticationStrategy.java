package pl.smsapi.api.authenticationStrategy;

public class BearerAuthenticationStrategy implements AuthenticationStrategy {
    private String token;

    public BearerAuthenticationStrategy(String token) {
        this.token = token;
    }

    @Override
    public String getAuthenticationHeader() {
        return "Bearer " + token;
    }
}
