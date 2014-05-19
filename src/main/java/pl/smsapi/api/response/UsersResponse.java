package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersResponse implements Response {

    private ArrayList<UserResponse> list = new ArrayList<UserResponse>();

    public UsersResponse(String data) {

        if (data != null && !data.isEmpty()) {

            JSONArray aData = new JSONArray(data);
            final int n = aData.length();
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    JSONObject tmp = aData.getJSONObject(i);
                    list.add(new UserResponse(tmp.optString("username"), tmp.optDouble("limit"), tmp.optDouble("month_limit"), tmp.optInt("senders"), tmp.optInt("phonebook"), tmp.optInt("active"), tmp.optString("info")));
                }
            }
        }

    }

    public ArrayList<UserResponse> getList() {
        return list;
    }
}
