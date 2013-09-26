package pl.smsapi.api.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class StatusResponse extends CountableResponse {

	private ArrayList<MessageResponse> list = new ArrayList<MessageResponse>();

	public StatusResponse(int count, JSONArray list) {

		super(count);

        final int n = list.length();
        for (int i = 0; i < n; i++) {
            JSONObject row = list.getJSONObject(i);
            this.list.add(new MessageResponse(row.optString("id"), row.optString("points"), row.optString("number"), row.optString("status"), row.optString("error"), row.optString("idx")));
        }
	}

	public ArrayList<MessageResponse> getList() {
		return list;
	}
}
