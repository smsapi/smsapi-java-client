package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class GroupsList extends AbstractAction<Groups> {
    @Override
    protected String endPoint() {
        return "contacts/groups";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Groups createResponse(String data) {
        return new Groups.GroupsFromJsonFactory().createFrom(new JSONObject(data));
    }
}
