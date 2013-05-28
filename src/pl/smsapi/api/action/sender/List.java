package pl.smsapi.api.action.sender;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "SendersResponse")
public class List extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {
		
		String query = "";

		query += paramsLoginToQuery();
		
		query += paramsOther();
		
		query += "&list=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/sender.do", query, null);
	}

}
