package pl.smsapi.api.action.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "UserResponse")
public class Get extends BaseAction {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/user.do", query, null);
	}

	public Get setUsername(String username) {
		params.put("get_user", username);
		return this;
	}
}
