package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "GroupResponse")
public class GroupGet extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/phonebook.do", query, null);
	}

	public GroupGet setGroup(String groupName) {
		params.put("get_group", groupName);
		return this;
	}
}
