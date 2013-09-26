package pl.smsapi.api.action.user;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.PointsResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GetPoints extends BaseAction<PointsResponse> {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();
		query += paramsOther();
		query += "&credits=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"user.do", query, null);
	}

    protected PointsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new PointsResponse(jsonObject.getDouble("points"));
    }
}
