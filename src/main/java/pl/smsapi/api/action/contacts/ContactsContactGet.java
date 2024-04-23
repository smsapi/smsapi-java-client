package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.contacts.ContactsContactResponse;

public class ContactsContactGet extends AbstractAction<ContactsContactResponse> {
    private String contactId;

    public ContactsContactGet contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    @Override
    protected String endPoint() {
        return "contacts/" + contactId;
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected ContactsContactResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
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
