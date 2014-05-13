package pl.smsapi.api.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class CheckNumberResponse extends CountableResponse {

	private ArrayList<NumberResponse> list = new ArrayList<NumberResponse>();

	public CheckNumberResponse(int count, String data) {

		super(count);

		if (data != null && !data.isEmpty()) {

			JSONArray aData = new JSONArray(data);
			final int n = aData.length();
			if (n > 0) {
				for (int i = 0; i < n; i++) {
					JSONObject tmp = aData.getJSONObject(i);
					list.add(new NumberResponse(tmp.optString("id"), tmp.optString("number"), tmp.optInt("mcc"), tmp.optInt("mnc"), tmp.optString("info"), tmp.optString("status"), tmp.optInt("date"), tmp.optInt("ported"), tmp.optInt("ported_from"), tmp.optString("price")) {
					});
				}
			}
		}
	}

	public ArrayList<NumberResponse> getList() {
		return list;
	}
}
