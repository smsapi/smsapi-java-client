package pl.smsapi.api.action.contacts;

public class ContactsGroupEdit extends AbstractContactsGroupControl<ContactsGroupEdit> {
    private String groupId;

    public ContactsGroupEdit groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    protected String endPoint() {
        return "contacts/groups/" + groupId;
    }

    @Override
    protected String httpMethod() {
        return "PUT";
    }
}
