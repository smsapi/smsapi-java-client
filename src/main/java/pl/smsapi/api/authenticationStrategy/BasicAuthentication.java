package pl.smsapi.api.authenticationStrategy;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.net.URLConnection;
import java.util.Map;

public class BasicAuthentication implements AuthenticationStrategy {
    @Override
    public void applyAuthentication(URLConnection connection, Map<String, ?> data) {
        String authStr = data.get("username") + ":" + data.get("password");
        authStr = Base64.encode(authStr.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + authStr);

        data.remove("username");
        data.remove("password");
    }
}
