package pl.smsapi.api.action.contacts.groups;

import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Permission implements Response {

    public final String groupId;
    public final String username;
    public final boolean write;
    public final boolean read;
    public final boolean send;

    private Permission(String groupId, String username, boolean write, boolean read, boolean send) {
        this.groupId = groupId;
        this.username = username;
        this.write = write;
        this.read = read;
        this.send = send;
    }

    public static class PermissionFromJsonFactory {
        public Permission createFrom(JSONObject jsonObject) {
            return new Permission(
                jsonObject.getString("group_id"),
                jsonObject.getString("username"),
                jsonObject.getBoolean("write"),
                jsonObject.getBoolean("read"),
                jsonObject.getBoolean("send")
            );
        }
    }
}
