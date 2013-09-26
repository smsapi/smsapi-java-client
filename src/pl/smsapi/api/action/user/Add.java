package pl.smsapi.api.action.user;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.UserResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class Add extends BaseAction<UserResponse> {

	@Override
	public URI uri() throws URISyntaxException {
		
		String query = "";

		query += paramsLoginToQuery();
		
		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"user.do", query, null);
	}

	public Add setUsername(String username) {
		params.put("add_user", username);
		return this;
	}

	public Add setPassword(String password) {
		params.put("pass", password);
		return this;
	}

	public Add setPasswordApi(String password) {
		params.put("pass_api", password);
		return this;
	}

	public Add setLimit(int limit) {
		params.put("limit", Integer.toString(limit));
		return this;
	}
	
	public Add setLimit(double limit) {
		params.put("limit", Double.toString(limit));
		return this;
	}

	public Add setMonthLimit(int limit) {
		params.put("month_limit", Integer.toString(limit));
		return this;
	}
	
	public Add setMonthLimit(double limit) {
		params.put("month_limit", Double.toString(limit));
		return this;
	}

	public Add setSenders(boolean access) {
		
		if (access == true)
			params.put("senders", "1");
		else
			params.put("senders", "0");
		
		return this;
	}

	public Add setPhonebook(boolean access) {
		
		if (access == true)
			params.put("phonebook", "1");
		else
			params.put("phonebook", "0");
		
		return this;
	}

	public Add setActive(boolean val) {
		
		if (val == true)
			params.put("active", "1");
		else
			params.put("active", "0");
		
		return this;
	}

	public Add setInfo(String info) {
		params.put("info", info);
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
