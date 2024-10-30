package pl.smsapi.api.action.contacts.groups;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class GroupDelete extends AbstractAction<RawResponse> {

    private final String id;

    public GroupDelete(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "contacts/groups/" + id;
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
