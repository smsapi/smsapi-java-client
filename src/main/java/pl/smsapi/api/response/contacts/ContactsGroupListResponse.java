package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.CountableResponse;

import java.util.ArrayList;

public class ContactsGroupListResponse extends CountableResponse {
    private ArrayList<ContactsGroupResponse> list = new ArrayList<ContactsGroupResponse>();

    public ContactsGroupListResponse(int count, JSONArray list) {
        super(count);

        final int n = list.length();
        for (int i = 0; i < n; i++) {
            JSONObject row = list.getJSONObject(i);
            this.list.add(new ContactsGroupResponse(
                    row.optString("id"),
                    row.optString("name"),
                    row.optString("description"),
                    row.optString("date_created"),
                    row.optString("date_updated"),
                    row.optString("created_by"),
                    row.optString("idx"),
                    row.optJSONArray("permissions")
            ));
        }
    }

    public ArrayList<ContactsGroupResponse> getList() {
        return list;
    }
}
