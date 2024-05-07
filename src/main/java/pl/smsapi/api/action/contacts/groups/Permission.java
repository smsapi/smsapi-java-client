package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Permission implements Response {

    private final String groupId;
    private final String username;
    private final boolean write;
    private final boolean read;
    private final boolean send;

    public Permission(String groupId, String username, boolean write, boolean read, boolean send) {
        this.groupId = groupId;
        this.username = username;
        this.write = write;
        this.read = read;
        this.send = send;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isWrite() {
        return write;
    }

    public boolean isRead() {
        return read;
    }

    public boolean isSend() {
        return send;
    }

    public static class PermissionFromJsonFactory {
        public Permission createFrom(JSONObject jsonObject) {
            return new Permission(
                jsonObject.optString("group_id"),
                jsonObject.optString("username"),
                jsonObject.optBoolean("write"),
                jsonObject.optBoolean("read"),
                jsonObject.optBoolean("send")
            );
        }
    }
}
