package pl.smsapi.api.action.contacts;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class ContactsContactDelete extends AbstractAction<RawResponse> {
    private String contactId;

    public ContactsContactDelete contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    @Override
    protected String endPoint() {
        return contactId;
    }

    @Override
    protected String httpMethod() {
        return "DELETE";
    }

    @Override
    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
