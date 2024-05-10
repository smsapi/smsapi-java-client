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

    public void withPassword(String password) {
        params.put("credentials[password]", password);
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
