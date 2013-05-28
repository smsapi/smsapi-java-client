package pl.smsapi.api.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroupsResponse extends CountableResponse {

	private ArrayList<GroupResponse> list = new ArrayList<GroupResponse>();

	public GroupsResponse(int count, String data) {

		super(count);

		if (data != null && !data.isEmpty()) {

			JSONArray aData = new JSONArray(data);
			final int n = aData.length();
			if (n > 0) {
				for (int i = 0; i < n; i++) {
					JSONObject tmp = aData.getJSONObject(i);
					list.add(new GroupResponse(tmp.optString("name"), tmp.optString("info"), tmp.optInt("numbers_count")));
				}
			}
		}

	}

	public ArrayList<GroupResponse> getList() {
		return list;
	}
}
