package pl.smsapi.proxy;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;

import java.io.InputStream;
import java.util.Map;

public interface Proxy {

    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files) throws Exception;
    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files, String httpMethod, AuthenticationStrategy authenticationStrategy) throws Exception;
}
