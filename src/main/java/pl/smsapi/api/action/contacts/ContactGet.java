package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class ContactGet extends AbstractAction<Contact> {

    private final String id;

    public ContactGet(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "contacts/" + id;
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Contact createResponse(String data) {
        return new Contact.ContactFromJsonFactory().createFrom(new JSONObject(data));
    }
}
