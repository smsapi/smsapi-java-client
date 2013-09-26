package pl.smsapi.api.action.sender;

import java.net.URI;
import java.net.URISyntaxException;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.RawResponse;

public class Default extends BaseAction<RawResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"sender.do", query, null);
	}

	public Default setName(String sendername) {
		params.put("default", sendername);
		return this;
	}

    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
