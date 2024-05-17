package pl.smsapi.api.action.contacts.groups;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Group implements Response {

    public final String id;
    public final String name;
    public final String description;
    public final String dateCreated;
    public final String dateUpdated;
    public final String createdBy;
    public final String idx;
    public final Permissions permissions;

    private Group(String id, String name, String description, String dateCreated, String dateUpdated, String createdBy, String idx, JSONArray permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.createdBy = createdBy;
        this.idx = idx;
        this.permissions = new Permissions.PermissionsFromJsonFactory().createFrom(permissions);
    }

    static class GroupFromJsonFactory {
        public Group createFrom(JSONObject jsonObject) {
            return new Group(
                jsonObject.getString("id"),
                jsonObject.getString("name"),
                jsonObject.getString("description"),
                jsonObject.getString("date_created"),
                jsonObject.getString("date_updated"),
                jsonObject.getString("created_by"),
                jsonObject.optString("idx", null),
                jsonObject.optJSONArray("permissions")
            );
        }
    }
}
