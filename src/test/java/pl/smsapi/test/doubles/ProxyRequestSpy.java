package pl.smsapi.test.doubles;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.proxy.Proxy;

import java.io.InputStream;
import java.util.Map;

public class ProxyRequestSpy implements Proxy {

    private final String responseDummy;
    public String requestEndpoint;
    public Map<String, String> requestPayload;
    public String requestMethod;

    public ProxyRequestSpy(String responseDummy) {
        this.responseDummy = responseDummy;
    }

    @Override
    public String execute(String endpoint, Map<String, String> data, Map<String, InputStream> files, String httpMethod, AuthenticationStrategy authenticationStrategy) throws Exception {
        requestMethod = httpMethod;
        requestEndpoint = endpoint;
        requestPayload = data;
        return responseDummy;
    }
}
