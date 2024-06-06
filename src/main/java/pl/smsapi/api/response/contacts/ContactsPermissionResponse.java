package pl.smsapi.api.response.contacts;

import pl.smsapi.api.response.Response;

/**
 * @deprecated use {@link pl.smsapi.api.action.contacts.groups.Permission()} instead
 */
@Deprecated
public class ContactsPermissionResponse implements Response {
    private String groupId;
    private String username;
    private boolean write;
    private boolean read;
    private boolean send;

    public ContactsPermissionResponse(String groupId, String username, boolean write, boolean read, boolean send) {
        this.groupId = groupId;
        this.username = username;
        this.write = write;
        this.read = read;
        this.send = send;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}
