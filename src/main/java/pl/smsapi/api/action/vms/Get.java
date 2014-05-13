package pl.smsapi.api.action.vms;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.StatusResponse;

public class Get extends BaseAction<StatusResponse> {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		String tmp[] = new String[this.id.size()];
		this.id.toArray(tmp);

		query += "&status=" + join(tmp, "|");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"vms.do", query, null);
	}

	public Get id(String id) {
		this.id.add(id);
		return this;
	}

	public Get ids(String[] ids) {
		for (String item : ids) {
			id(item);
		}
		return this;
	}

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }
}
