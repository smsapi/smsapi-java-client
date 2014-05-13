
package pl.smsapi.proxy;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.exception.ProxyException;

public interface Proxy {
	
	public String execute(BaseAction action) throws ProxyException;
	public String getProtocol();
	public String getHost();
	public int getPort();
    public String getPath();
}
