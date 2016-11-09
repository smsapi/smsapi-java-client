package pl.smsapi.api.authenticationStrategy;

import java.util.Map;
import org.apache.commons.codec.binary.Base64;

public class BasicAuthentication implements AuthenticationStrategy {
    @Override
    public String getAuthenticationHeader(Map<String, ?> data) {
        String authStr = data.get("username") + ":" + data.get("password");
        authStr = Base64.encodeBase64String(authStr.getBytes());

        return "Basic " + authStr;
    }
}
