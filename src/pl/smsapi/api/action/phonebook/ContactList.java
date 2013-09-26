package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.ContactsResponse;

public class ContactList extends BaseAction<ContactsResponse> {

	private ArrayList<String> groups = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		if (!groups.isEmpty()) {
			String tmp[] = new String[groups.size()];
			groups.toArray(tmp);
			query += "&groups=" + join(tmp, ";");
		}

		query += "&list_contacts=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public ContactList setNumber(String number) {
		params.put("number", number);
		return this;
	}

	public ContactList setGroup(String group) {
		groups.add(group);
		return this;
	}

	public ContactList setGroups(String[] groups) {
		for (String item : groups) {
			setGroup(item);
		}
		return this;
	}

	public ContactList setText(String text) {
		params.put("text_search", text);
		return this;
	}

	public ContactList setGender(String gender) {
		params.put("gender", gender);
		return this;
	}

	public ContactList setOrderBy(String orderBy) {
		params.put("order_by", orderBy);
		return this;
	}

	public ContactList setOrderDir(String orderDir) {
		params.put("order_dir", orderDir);
		return this;
	}

	public ContactList setLimit(int limit) {
		params.put("limit", Integer.toString(limit));
		return this;
	}

	public ContactList setOffset(int offset) {
		params.put("offset", Integer.toString(offset));
		return this;
	}

    protected ContactsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsResponse(jsonObject.getInt("count"), jsonObject.getJSONArray("list"));
    }
}
