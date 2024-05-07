package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class Subuser implements Response {
    public final String id;
    public final String username;
    public final boolean active;
    public final String description;
    public final double pointsFromAccount;
    public final double pointsPerMonth;

    private Subuser(String id, String username, boolean active, String description, double pointsFromAccount, double pointsPerMonth) {
        this.id = id;
        this.username = username;
        this.active = active;
        this.description = description;
        this.pointsFromAccount = pointsFromAccount;
        this.pointsPerMonth = pointsPerMonth;
    }

    public static class SubuserFromJsonFactory {
        public Subuser createFrom(JSONObject jsonObject) {
            return new Subuser(
                jsonObject.getString("id"),
                jsonObject.getString("username"),
                jsonObject.getBoolean("active"),
                jsonObject.optString("description"),
                jsonObject.getJSONObject("points").getDouble("from_account"),
                jsonObject.getJSONObject("points").getDouble("per_month")
            );
        }
    }
}
