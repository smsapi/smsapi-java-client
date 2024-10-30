package pl.smsapi.api.action.contacts;

/**
 * @deprecated use @link ContactAdd instead
 */
@Deprecated
public class ContactsContactAdd extends AbstractContactsContactControl<ContactsContactAdd> {

    @Override
    protected String endPoint() {
        return "";
    }

    @Override
    protected String httpMethod() {
        return "POST";
    }
}
