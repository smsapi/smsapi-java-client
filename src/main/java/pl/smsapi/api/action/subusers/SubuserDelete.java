package pl.smsapi.api.action.subusers;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.NoContentResponse;

public class SubuserDelete extends AbstractAction<NoContentResponse> {
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
    protected NoContentResponse createResponse(String data) {
        return new NoContentResponse();
    }
}
