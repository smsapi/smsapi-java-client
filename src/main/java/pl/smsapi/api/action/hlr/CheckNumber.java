package pl.smsapi.api.action.hlr;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CheckNumberResponse;

public class CheckNumber extends BaseAction<CheckNumberResponse> {

	private ArrayList<String> numbers = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		String tmp[] = new String[this.numbers.size()];
		this.numbers.toArray(tmp);

		query += "&number=" + join(tmp, ",");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"hlrsync.do", query, null);
	}

	public CheckNumber setNumber(String number) {
		this.numbers.add(number);
		return this;
	}

	public CheckNumber setNumber(String[] number) {
		for (String item : number) {
			setNumber(item);
		}
		return this;
	}

    protected CheckNumberResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CheckNumberResponse(jsonObject.getInt("count"), jsonObject.getString("list"));
    }
}
