package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.GroupResponse;

public class PhonebookGroupGet extends BaseAction<GroupResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public PhonebookGroupGet setGroup(String groupName) {
		params.put("get_group", groupName);
		return this;
	}

    protected GroupResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new GroupResponse(jsonObject.getString("name"), jsonObject.optString("info"), jsonObject.optInt("numbers_count"));
    }
}
