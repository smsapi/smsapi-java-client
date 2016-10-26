package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.CountableResponse;

import java.util.ArrayList;

public class ContactsPermissionListResponse extends CountableResponse {
    private ArrayList<ContactsPermissionResponse> list = new ArrayList<ContactsPermissionResponse>();

    public ContactsPermissionListResponse(int count, JSONArray list) {
        super(count);

        final int n = list.length();
        for (int i = 0; i < n; i++) {
            JSONObject row = list.getJSONObject(i);
            this.list.add(new ContactsPermissionResponse(
                    row.optString("group_id"),
                    row.optString("username"),
                    row.optBoolean("write"),
                    row.optBoolean("read"),
                    row.optBoolean("send")
            ));
        }
    }

    public ArrayList<ContactsPermissionResponse> getList() {
        return list;
    }
}
