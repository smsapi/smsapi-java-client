package pl.smsapi.api.action.subusers;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.Response;

public class SubuserDelete extends AbstractAction<Response> {
    private final String id;

    public SubuserDelete(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "subusers/" + id;
    }

    @Override
    protected String httpMethod() {
        return "DELETE";
    }

    @Override
    protected Response createResponse(String data) {
        return null;
    }
}
