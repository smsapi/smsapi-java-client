package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.RawResponse;

public class PhonebookGroupDelete extends BaseAction<RawResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public PhonebookGroupDelete setGroup(String groupname) {
		params.put("delete_group", groupname);
		return this;
	}

	public PhonebookGroupDelete removeContacts(boolean remove) {
		if (remove == true) {
			params.put("remove_contacts", "1");
		} else if (remove == false) {
			params.remove("remove_contacts");
		}

		return this;
	}

    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
