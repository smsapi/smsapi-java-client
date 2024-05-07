package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class GroupAdd extends AbstractAction<Group> {

    public GroupAdd(String name) {
        params.put("name", name);
    }

    @Override
    protected String endPoint() {
        return "contacts/groups";
    }

    public GroupAdd setDescription(String description) {
        params.put("description", description);
        return this;
    }

    public GroupAdd setIdx(String idx) {
        params.put("idx", idx);
        return this;
    }

    @Override
    protected Group createResponse(String data) {
        return new Group.GroupFromJsonFactory().createFrom(new JSONObject(data));
    }
}
