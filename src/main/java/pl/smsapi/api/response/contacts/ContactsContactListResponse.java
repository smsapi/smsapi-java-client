package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

/**
 * @deprecated use @link pl.smsapi.api.action.contacts.Contacts() instead
 */
@Deprecated
public class ContactsContactListResponse extends ListResponse<ContactsContactResponse> {

    public ContactsContactListResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected ContactsContactResponse buildItem(JSONObject jsonObject) {
        return new ContactsContactResponse(
            jsonObject.getString("id"),
            jsonObject.optString("first_name"),
            jsonObject.optString("last_name"),
            jsonObject.optString("birthday_date"),
            jsonObject.optString("phone_number"),
            jsonObject.optString("email"),
            jsonObject.optString("gender"),
            jsonObject.optString("city"),
            jsonObject.optString("country"),
            jsonObject.optString("date_created"),
            jsonObject.optString("date_updated"),
            jsonObject.optString("description"),
            jsonObject.optJSONArray("groups"),
            jsonObject.optString("source")
        );
    }
}