package pl.smsapi.test.doubles;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.proxy.Proxy;

import java.io.InputStream;
import java.util.Map;

public class ProxyResponseStub implements Proxy {

    private final String rawResponse;

    public ProxyResponseStub(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    @Override
    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files, String httpMethod, AuthenticationStrategy authenticationStrategy) throws Exception {
        return rawResponse;
    }
}
