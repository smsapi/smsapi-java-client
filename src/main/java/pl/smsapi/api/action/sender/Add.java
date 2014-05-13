package pl.smsapi.api.action.sender;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;

public class Add extends BaseAction<CountableResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"sender.do", query, null);
	}

	public Add setName(String sendername) {
		params.put("add", sendername);
		return this;
	}

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }
}
