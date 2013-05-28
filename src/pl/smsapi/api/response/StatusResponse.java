package pl.smsapi.api.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class StatusResponse extends CountableResponse {

	private ArrayList<MessageResponse> list = new ArrayList<MessageResponse>();

	public StatusResponse(int count, String data) {

		super(count);

		if (data != null && !data.isEmpty()) {

			JSONArray aData = new JSONArray(data);
			final int n = aData.length();
			if (n > 0) {
				for (int i = 0; i < n; i++) {
					JSONObject tmp = aData.getJSONObject(i);
					list.add(new MessageResponse(tmp.optString("id"), tmp.optString("points"), tmp.optString("number"), tmp.optString("status"), tmp.optString("error"), tmp.optString("idx")));
				}
			}
		}

	}

	public ArrayList<MessageResponse> getList() {
		return list;
	}
}
