package pl.smsapi.api.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContactsResponse extends CountableResponse {

	private ArrayList<ContactResponse> list = new ArrayList<ContactResponse>();

	public ContactsResponse(int count, String data) {

		super(count);

		if (data != null && !data.isEmpty()) {

			JSONArray aData = new JSONArray(data);
			final int n = aData.length();
			if (n > 0) {
				for (int i = 0; i < n; i++) {
					JSONObject tmp = aData.getJSONObject(i);
					list.add(new ContactResponse(tmp.optString("number"), tmp.optString("first_name"), tmp.optString("last_name"), tmp.optString("info"), tmp.optString("birthday"), tmp.optString("city"), tmp.optString("gender"), tmp.optInt("date_add"), tmp.optInt("date_mod")));
				}
			}
		}
	}

	public ArrayList<ContactResponse> getList() {
		return list;
	}
}
