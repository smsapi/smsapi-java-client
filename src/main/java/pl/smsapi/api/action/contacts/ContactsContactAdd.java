package pl.smsapi.api.action.contacts;

public class ContactsContactAdd extends AbstractContactsContactControl<ContactsContactAdd> {

    @Override
    protected String endPoint() {
        return "contacts";
    }

    @Override
    protected String httpMethod() {
        return "POST";
    }
}
