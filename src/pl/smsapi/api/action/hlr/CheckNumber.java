package pl.smsapi.api.action.hlr;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "CheckNumberResponse")
public class CheckNumber extends BaseAction {

	private ArrayList<String> numbers = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsOther();

		String tmp[] = new String[this.numbers.size()];
		this.numbers.toArray(tmp);

		query += "&number=" + join(tmp, ",");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/hlrsync.do", query, null);
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
}
