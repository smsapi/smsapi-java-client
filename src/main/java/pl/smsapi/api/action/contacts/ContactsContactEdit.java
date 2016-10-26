package pl.smsapi.api.action.contacts;

public class ContactsContactEdit extends AbstractContactsContactControl<ContactsContactEdit> {
    private String contactId;

    public ContactsContactEdit contactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    @Override
    protected String endPoint() {
        return contactId;
    }

    @Override
    protected String httpMethodOverride() {
        return "PUT";
    }
}
