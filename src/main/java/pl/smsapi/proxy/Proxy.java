package pl.smsapi.proxy;

import java.io.InputStream;
import java.util.Map;

public interface Proxy {

    public String execute(String endpoint, Map<String, ?> data, Map<String, InputStream> files) throws Exception;
    public String execute(String endpoint, Map<String, ?> data, Map<String, InputStream> files, String httpMethod) throws Exception;
}
