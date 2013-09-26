
package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Delete extends BaseAction<CountableResponse> {

	private ArrayList<String> id = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {
					
		String query;

		query = paramsLoginToQuery();
		
		query += paramsOther();
		 	
		String tmp[] = new String[this.id.size()];
		this.id.toArray(tmp);
		
		query += "&sch_del=" + join(tmp, "|");

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"mms.do", query, null);
	}
	
	public Delete id(String id) {
		this.id.add(id);
		return this;
	}

	public Delete ids(String[] ids) {		
		for(String item: ids){
			id(item);
		}
		return this;
	}

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }
}
