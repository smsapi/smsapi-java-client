package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactsResponse extends CountableResponse {

    private ArrayList<ContactResponse> list = new ArrayList<ContactResponse>();

    public ContactsResponse(int count, JSONArray list) {

        super(count);

        final int n = list.length();
        for (int i = 0; i < n; i++) {
            JSONObject row = list.getJSONObject(i);
            this.list.add(new ContactResponse(row.getString("number"), row.optString("first_name"), row.optString("last_name"), row.optString("info"), row.optString("birthday"), row.optString("city"), row.optString("gender"), row.optInt("date_add"), row.optInt("date_mod")));
        }
    }

    public ArrayList<ContactResponse> getList() {
        return list;
    }
}
