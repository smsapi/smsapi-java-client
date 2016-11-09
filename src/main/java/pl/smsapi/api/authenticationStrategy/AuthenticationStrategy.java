package pl.smsapi.api.authenticationStrategy;

import java.util.Map;

public interface AuthenticationStrategy {
    String getAuthenticationHeader(Map<String, ?> data);
}
