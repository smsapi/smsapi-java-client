package pl.smsapi.api.action.sender;

import java.net.URI;
import java.net.URISyntaxException;

import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.SendersResponse;


public class List extends BaseAction<SendersResponse> {

	@Override
	public URI uri() throws URISyntaxException {
		
		String query = "";

		query += paramsLoginToQuery();
		query += paramsOther();
		query += "&list=1";

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"sender.do", query, null);
	}


    protected SendersResponse createResponse(String data) {
        return new SendersResponse(data);
    }
}
