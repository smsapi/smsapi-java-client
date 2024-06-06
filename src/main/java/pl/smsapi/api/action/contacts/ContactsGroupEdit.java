package pl.smsapi.api.action.contacts;

import pl.smsapi.api.action.contacts.groups.GroupEdit;

/**
 * @deprecated use {@link GroupEdit} instead
 */
@Deprecated
public class ContactsGroupEdit extends AbstractContactsGroupControl<ContactsGroupEdit> {
    private String groupId;

    public ContactsGroupEdit groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    protected String endPoint() {
        return "groups/" + groupId;
    }

    @Override
    protected String httpMethod() {
        return "PUT";
    }
}
