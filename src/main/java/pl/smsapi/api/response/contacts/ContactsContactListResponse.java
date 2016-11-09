package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.CountableResponse;

import java.util.ArrayList;

public class ContactsContactListResponse extends CountableResponse {
    private ArrayList<ContactsContactResponse> list = new ArrayList<ContactsContactResponse>();

    public ContactsContactListResponse(int count, JSONArray list) {
        super(count);

        final int n = list.length();
        for (int i = 0; i < n; i++) {
            JSONObject row = list.getJSONObject(i);
            this.list.add(new ContactsContactResponse(
                    row.getString("id"),
                    row.optString("first_name"),
                    row.optString("last_name"),
                    row.optString("birthday_date"),
                    row.optString("phone_number"),
                    row.optString("email"),
                    row.optString("gender"),
                    row.optString("city"),
                    row.optString("country"),
                    row.optString("date_created"),
                    row.optString("date_updated"),
                    row.optString("description"),
                    row.optJSONArray("groups"),
                    row.optString("source")
            ));
        }
    }

    public ArrayList<ContactsContactResponse> getList() {
        return list;
    }
}
