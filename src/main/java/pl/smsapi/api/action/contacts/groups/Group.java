package pl.smsapi.api.action.contacts.groups;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Group implements Response {

    private final String id;
    private final String name;
    private final String description;
    private final String dateCreated;
    private final String dateUpdated;
    private final String createdBy;
    private final String idx;
    private final Permissions permissions;

    public Group(String id, String name, String description, String dateCreated, String dateUpdated, String createdBy, String idx, JSONArray permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.createdBy = createdBy;
        this.idx = idx;
        this.permissions = new Permissions.PermissionsFromJsonFactory().createFrom(permissions);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getIdx() {
        return idx;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public static class GroupFromJsonFactory {
        public Group createFrom(JSONObject jsonObject) {
            return new Group(
                jsonObject.optString("id"),
                jsonObject.optString("name"),
                jsonObject.optString("description"),
                jsonObject.optString("date_created"),
                jsonObject.optString("date_updated"),
                jsonObject.optString("created_by"),
                jsonObject.optString("idx"),
                jsonObject.optJSONArray("permissions")
            );
        }
    }
}
