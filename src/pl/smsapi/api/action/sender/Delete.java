package pl.smsapi.api.action.sender;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "CountableResponse")
public class Delete extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/sender.do", query, null);
	}

	public Delete setSender(String sendername) {
		params.put("delete", sendername);
		return this;
	}
}
