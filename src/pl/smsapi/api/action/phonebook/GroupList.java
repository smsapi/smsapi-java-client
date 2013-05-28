package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "GroupsResponse")
public class GroupList extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		query += "&list_groups=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/phonebook.do", query, null);
	}
}
