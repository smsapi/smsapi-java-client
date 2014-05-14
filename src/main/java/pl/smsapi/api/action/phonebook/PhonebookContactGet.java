package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.ContactResponse;

public class PhonebookContactGet extends BaseAction<ContactResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public PhonebookContactGet setContact(String number) {
		params.put("get_contact", number);
		return this;
	}

    protected ContactResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return
                new ContactResponse(
                        jsonObject.getString("number"),
                        jsonObject.optString("first_name"),
                        jsonObject.optString("last_name"),
                        jsonObject.optString("info"),
                        jsonObject.optString("birthday"),
                        jsonObject.optString("city"),
                        jsonObject.optString("gender"),
                        jsonObject.optInt("date_add"),
                        jsonObject.optInt("date_mod")
                );
    }
}
