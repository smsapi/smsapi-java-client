
package pl.smsapi.proxy;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.exception.ProxyException;

import java.io.InputStream;
import java.util.Map;

public interface Proxy {

    public String execute(String endpoint, Map<String,? extends Object> data, Map<String, InputStream> files) throws Exception;

	public String execute(BaseAction action) throws ProxyException;
	public String getProtocol();
	public String getHost();
	public int getPort();
    public String getPath();
}
