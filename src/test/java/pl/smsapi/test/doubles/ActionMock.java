package pl.smsapi.test.doubles;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.Response;

public class ActionMock extends AbstractAction<Response> {
    @Override
    protected String endPoint() {
        return null;
    }

    @Override
    protected Response createResponse(String data) {
        return null;
    }

    @Override
    protected void handleError(String text) {
    }
}