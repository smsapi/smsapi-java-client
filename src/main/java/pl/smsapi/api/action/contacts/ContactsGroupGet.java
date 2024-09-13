package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.action.contacts.groups.GroupGet;
import pl.smsapi.api.response.contacts.ContactsGroupResponse;

/**
 * @deprecated use @link GroupGet instead
 */
@Deprecated
public class ContactsGroupGet extends AbstractAction<ContactsGroupResponse> {
    private String groupId;

    public ContactsGroupGet groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    protected String endPoint() {
        return "groups/" + groupId;
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected ContactsGroupResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsGroupResponse(
                jsonObject.getString("id"),
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
