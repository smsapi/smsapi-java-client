package pl.smsapi.api.action.phonebook;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.RawResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class PhonebookContactDelete extends BaseAction<RawResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public PhonebookContactDelete setContact(String number) {
		params.put("delete_contact", number);
		return this;
	}

    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
