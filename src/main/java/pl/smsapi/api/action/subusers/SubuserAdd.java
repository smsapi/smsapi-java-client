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

    public void withApiPassword(String apiPassword) {
        params.put("credentials[api_password]", apiPassword);
    }

    public void asActive() {
        params.put("active", "1");
    }

    public void asInactive() {
        params.put("active", "0");
    }

    public void withDescription(String description) {
        params.put("description", description);
    }

    public void withPointsFromAccount(double pointsFromAccount) {
        params.put("points[from_account]", String.valueOf(pointsFromAccount));
    }

    public void withPointsPerMonth(double pointsPerMonth) {
        params.put("points[per_month]", String.valueOf(pointsPerMonth));
    }
}
