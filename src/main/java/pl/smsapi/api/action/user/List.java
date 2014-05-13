package pl.smsapi.api.action.user;

import java.net.URI;
import java.net.URISyntaxException;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.UsersResponse;

public class List extends BaseAction<UsersResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		query += "&list=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"user.do", query, null);
	}

    protected UsersResponse createResponse(String data) {
        return new UsersResponse(data);
    }
}
