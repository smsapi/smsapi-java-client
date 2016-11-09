package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import pl.smsapi.api.response.Response;

public class ContactsGroupResponse implements Response {
    private String id;
    private String name;
    private String description;
    private String dateCreated;
    private String dateUpdated;
    private String createdBy;
    private String idx;
    private ContactsPermissionListResponse permissions;

    public ContactsGroupResponse(String id, String name, String description, String dateCreated, String dateUpdated, String createdBy, String idx, JSONArray permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.createdBy = createdBy;
        this.idx = idx;
        this.permissions = parsePermissions(permissions);
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

    public ContactsPermissionListResponse getPermissions() {
        return permissions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public void setPermissions(ContactsPermissionListResponse permissions) {
        this.permissions = permissions;
    }

    private ContactsPermissionListResponse parsePermissions(JSONArray permissions) {
        if (permissions == null) {
            return new ContactsPermissionListResponse(0, new JSONArray());
        }

        return new ContactsPermissionListResponse(permissions.length(), permissions);
    }
}
