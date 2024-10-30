package pl.smsapi.api.action.sms.sendernames;

import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Sendername implements Response {

    public final String sender;
    public final String status;
    public final boolean isDefault;
    public final String createdAt;

    private Sendername(String sender, String status, boolean isDefault, String createdAt) {
        this.sender = sender;
        this.status = status;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
    }

    static class SendernameFactory {
        public Sendername createFrom(JSONObject jsonObject) {
            return new Sendername(
                jsonObject.getString("sender"),
                jsonObject.getString("status"),
                jsonObject.getBoolean("is_default"),
                jsonObject.getString("created_at")
            );
        }
    }
}
