package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.action.contacts.groups.GroupsList;
import pl.smsapi.api.response.contacts.ContactsGroupListResponse;

/**
 * @deprecated use {@link GroupsList} instead
 */
@Deprecated
public class ContactsGroupList extends AbstractAction<ContactsGroupListResponse> {
    @Override
    protected String endPoint() {
        return "groups";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected ContactsGroupListResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsGroupListResponse(jsonObject.getInt("size"), jsonObject.getJSONArray("collection"));
    }
}
