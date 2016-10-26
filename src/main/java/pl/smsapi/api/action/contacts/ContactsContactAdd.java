package pl.smsapi.api.action.contacts;

public class ContactsContactAdd extends AbstractContactsContactControl<ContactsContactAdd> {

    @Override
    protected String endPoint() {
        return "";
    }

    @Override
    protected String httpMethodOverride() {
        return "POST";
    }
}
