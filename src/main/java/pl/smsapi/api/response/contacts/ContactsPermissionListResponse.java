package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

/**
 * @deprecated use {@link pl.smsapi.api.action.contacts.groups.Permissions()} instead
 */
@Deprecated
public class ContactsPermissionListResponse extends ListResponse<ContactsPermissionResponse> {

    public ContactsPermissionListResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected ContactsPermissionResponse buildItem(JSONObject jsonObject) {
        return new ContactsPermissionResponse(
            jsonObject.optString("group_id"),
            jsonObject.optString("username"),
            jsonObject.optBoolean("write"),
            jsonObject.optBoolean("read"),
            jsonObject.optBoolean("send")
        );
    }
}
