package pl.smsapi.test.doubles;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.proxy.Proxy;

import java.io.InputStream;
import java.util.Map;

public class ProxyMock implements Proxy {
    public AuthenticationStrategy lastUsedAuthenticationStrategy;

    @Override
    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files, String httpMethod, AuthenticationStrategy authenticationStrategy) throws Exception {
        lastUsedAuthenticationStrategy = authenticationStrategy;
        return null;
    }
}