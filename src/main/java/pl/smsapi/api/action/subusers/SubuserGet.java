package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.subusers.SubuserResponse;

public class SubuserGet extends AbstractAction<SubuserResponse> {
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
    protected SubuserResponse createResponse(String data) {
        return new SubuserResponse.SubuserFromJsonFactory().createFrom(new JSONObject(data));
    }
}
