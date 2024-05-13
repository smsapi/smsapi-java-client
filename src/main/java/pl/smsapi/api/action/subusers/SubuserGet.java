package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SubuserGet extends AbstractAction<Subuser> {
    private final String id;

    public SubuserGet(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "subusers/" + id;
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Subuser createResponse(String data) {
        return new Subuser.SubuserFromJsonFactory().createFrom(new JSONObject(data));
    }
}
