package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class GroupEdit extends AbstractAction<Group> {

    private final String id;

    public GroupEdit(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "contacts/groups/" + id;
    }

    @Override
    protected String httpMethod() {
        return "PUT";
    }

    public GroupEdit withName(String name) {
        params.put("name", name);
        return this;
    }

    public GroupEdit withDescription(String description) {
        params.put("description", description);
        return this;
    }

    public GroupEdit withIdx(String idx) {
        params.put("idx", idx);
        return this;
    }

    @Override
    protected Group createResponse(String data) {
        return new Group.GroupFromJsonFactory().createFrom(new JSONObject(data));
    }
}
