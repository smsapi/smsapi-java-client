package pl.smsapi.api.response.subusers;

import org.json.JSONObject;
import pl.smsapi.api.response.Response;

public class SubuserResponse implements Response {
    private final String id;
    private final String username;
    private final boolean active;
    private final String description;
    private final double pointsFromAccount;
    private final double pointsPerMonth;

    public SubuserResponse(String id, String username, boolean active, String description, double pointsFromAccount, double pointsPerMonth) {
        this.id = id;
        this.username = username;
        this.active = active;
        this.description = description;
        this.pointsFromAccount = pointsFromAccount;
        this.pointsPerMonth = pointsPerMonth;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public double getPointsFromAccount() {
        return pointsFromAccount;
    }

    public double getPointsPerMonth() {
        return pointsPerMonth;
    }

    public static class SubuserFromJsonFactory {
        public SubuserResponse createFrom(JSONObject jsonObject) {
            return new SubuserResponse(
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
