package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class ContactsGroupListResponse extends ListResponse<ContactsGroupResponse> {

    public ContactsGroupListResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected ContactsGroupResponse buildItem(JSONObject jsonObject) {
        return new ContactsGroupResponse(
            jsonObject.optString("id"),
            jsonObject.optString("name"),
            jsonObject.optString("description"),
            jsonObject.optString("date_created"),
            jsonObject.optString("date_updated"),
            jsonObject.optString("created_by"),
            jsonObject.optString("idx"),
            jsonObject.optJSONArray("permissions")
        );
    }
}
