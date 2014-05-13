package pl.smsapi.api.action.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.UserResponse;

public class Get extends BaseAction<UserResponse> {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"user.do", query, null);
	}

	public Get setUsername(String username) {
		params.put("get_user", username);
		return this;
	}

    protected UserResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return
                new UserResponse(
                        jsonObject.getString("username"),
                        jsonObject.optDouble("limit"),
                        jsonObject.optDouble("month_limit"),
                        jsonObject.optInt("senders"),
                        jsonObject.optInt("phonebook"),
                        jsonObject.optInt("active"),
                        jsonObject.optString("info")
                );
    }
}
