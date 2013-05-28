package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "GroupResponse")
public class GroupAdd extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/phonebook.do", query, null);
	}

	public GroupAdd setName(String groupName) {
		params.put("add_group", groupName);
		return this;
	}

	public GroupAdd setInfo(String info) {
		params.put("info", info);
		return this;
	}
}
