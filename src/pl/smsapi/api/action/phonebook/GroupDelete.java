package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "RawResponse")
public class GroupDelete extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/phonebook.do", query, null);
	}

	public GroupDelete setGroup(String groupname) {
		params.put("delete_group", groupname);
		return this;
	}

	public GroupDelete removeContacts(boolean remove) {
		if (remove == true) {
			params.put("remove_contacts", "1");
		} else if (remove == false) {
			params.remove("remove_contacts");
		}

		return this;
	}
}
