package pl.smsapi.api.action.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "PointsResponse")
public class GetPoints extends BaseAction {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		query += "&credits=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/user.do", query, null);
	}
}
