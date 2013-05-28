package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "RawResponse")
public class ContactDelete extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/phonebook.do", query, null);
	}

	public ContactDelete setContact(String number) {
		params.put("delete_contact", number);
		return this;
	}
}
