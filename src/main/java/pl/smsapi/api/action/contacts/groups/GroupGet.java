package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class GroupGet extends AbstractAction<Group> {

    private final String id;

    public GroupGet(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "contacts/groups/" + id;
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Group createResponse(String data) {
        return new Group.GroupFromJsonFactory().createFrom(new JSONObject(data));
    }
}
