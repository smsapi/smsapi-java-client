package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.contacts.ContactsContactListResponse;

public class ContactsContactList extends AbstractAction<ContactsContactListResponse> {
    @Override
    protected String endPoint() {
        return "";
    }

    @Override
    protected ContactsContactListResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsContactListResponse(jsonObject.getInt("size"), jsonObject.getJSONArray("collection"));
    }
}
