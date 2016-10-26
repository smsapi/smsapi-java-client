package pl.smsapi.api.action.contacts;

public class ContactsGroupAdd extends AbstractContactsGroupControl<ContactsGroupAdd> {

    @Override
    protected String endPoint() {
        return "groups";
    }

    @Override
    protected String httpMethodOverride() {
        return "POST";
    }
}
