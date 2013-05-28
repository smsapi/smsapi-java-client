package pl.smsapi.api.action.sms;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "StatusResponse")
public class Get extends BaseAction {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		String tmp[] = new String[this.id.size()];
		this.id.toArray(tmp);

		query += "&status=" + join(tmp, "|");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/sms.do", query, null);
	}

	public Get id(String id) {
		this.id.add(id);
		return this;
	}

	public Get ids(String[] ids) {
		for (String item : ids) {
			id(item);
		}
		return this;
	}
}
