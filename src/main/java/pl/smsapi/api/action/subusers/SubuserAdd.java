package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SubuserAdd extends AbstractAction<Subuser> {
    public SubuserAdd(String username, String password) {
        params.put("credentials[username]", username);
        params.put("credentials[password]", password);
    }

    @Override
    protected String endPoint() {
        return "subusers";
    }

    @Override
    protected Subuser createResponse(String data) {
        return new Subuser.SubuserFromJsonFactory().createFrom(new JSONObject(data));
    }

    public SubuserAdd withApiPassword(String apiPassword) {
        params.put("credentials[api_password]", apiPassword);
        return this;
    }

    public SubuserAdd asActive() {
        params.put("active", "1");
        return this;
    }

    public SubuserAdd asInactive() {
        params.put("active", "0");
        return this;
    }

    public SubuserAdd withDescription(String description) {
        params.put("description", description);
        return this;
    }

    public SubuserAdd withPointsFromAccount(double pointsFromAccount) {
        params.put("points[from_account]", String.valueOf(pointsFromAccount));
        return this;
    }

    public SubuserAdd withPointsPerMonth(double pointsPerMonth) {
        params.put("points[per_month]", String.valueOf(pointsPerMonth));
        return this;
    }
}
