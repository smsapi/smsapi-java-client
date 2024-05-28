package pl.smsapi.api.action.contacts;

import pl.smsapi.api.action.contacts.groups.GroupAdd;

/**
 * @deprecated use {@link GroupAdd} instead
 */
@Deprecated
public class ContactsGroupAdd extends AbstractContactsGroupControl<ContactsGroupAdd> {

    @Override
    protected String endPoint() {
        return "groups";
    }

    @Override
    protected String httpMethod() {
        return "POST";
    }
}
