package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.GroupsResponse;

public class GroupList extends BaseAction<GroupsResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		query += "&list_groups=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

    protected GroupsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new GroupsResponse(jsonObject.optInt("count"), jsonObject.optString("list"));
    }
}
