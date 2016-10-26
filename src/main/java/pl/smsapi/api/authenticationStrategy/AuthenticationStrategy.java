package pl.smsapi.api.authenticationStrategy;

import java.net.URLConnection;
import java.util.Map;

public interface AuthenticationStrategy {
    void applyAuthentication(URLConnection connection, Map<String, ?> data);
}
