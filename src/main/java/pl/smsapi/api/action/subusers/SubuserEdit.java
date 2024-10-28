package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SubuserEdit extends AbstractAction<Subuser> {
    private final String id;

    public SubuserEdit(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "subusers/" + id;
    }

    @Override
    protected String httpMethod() {
        return "PUT";
    }

    @Override
    protected Subuser createResponse(String data) {
        return new Subuser.SubuserFromJsonFactory().createFrom(new JSONObject(data));
    }

    public SubuserEdit withPassword(String password) {
        params.put("credentials[password]", password);
        return this;
    }

    public SubuserEdit withApiPassword(String apiPassword) {
        params.put("credentials[api_password]", apiPassword);
        return this;
    }

    public SubuserEdit asActive() {
        params.put("active", "1");
        return this;
    }

    public SubuserEdit asInactive() {
        params.put("active", "0");
        return this;
    }

    public SubuserEdit withDescription(String description) {
        params.put("description", description);
        return this;
    }

    public SubuserEdit withPointsFromAccount(double pointsFromAccount) {
        params.put("points[from_account]", String.valueOf(pointsFromAccount));
        return this;
    }

    public SubuserEdit withPointsPerMonth(double pointsPerMonth) {
        params.put("points[per_month]", String.valueOf(pointsPerMonth));
        return this;
    }
}
