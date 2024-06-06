package pl.smsapi.api.action.contacts;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.action.contacts.groups.GroupDelete;
import pl.smsapi.api.response.RawResponse;

/**
 * @deprecated use {@link GroupDelete} instead
 */
@Deprecated
public class ContactsGroupDelete extends AbstractAction<RawResponse> {
    private String groupId;

    public ContactsGroupDelete groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    protected String endPoint() {
        return "groups/" + groupId;
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
