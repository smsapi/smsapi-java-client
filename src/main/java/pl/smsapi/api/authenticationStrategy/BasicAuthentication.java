package pl.smsapi.api.authenticationStrategy;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.util.Map;

public class BasicAuthentication implements AuthenticationStrategy {
    @Override
    public String getAuthenticationHeader(Map<String, ?> data) {
        String authStr = data.get("username") + ":" + data.get("password");
        authStr = Base64.encode(authStr.getBytes());

        return "Basic " + authStr;
    }
}
