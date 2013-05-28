package pl.smsapi.api.action.vms;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "CountableResponse")
public class Delete extends BaseAction {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		String tmp[] = new String[this.id.size()];
		this.id.toArray(tmp);

		query += "&sch_del=" + join(tmp, "|");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/vms.do", query, null);
	}

	public Delete id(String id) {
		this.id.add(id);
		return this;
	}

	public Delete ids(String[] ids) {
		for (String item : ids) {
			id(item);
		}
		return this;
	}
}
