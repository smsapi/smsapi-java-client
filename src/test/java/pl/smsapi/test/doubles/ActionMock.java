package pl.smsapi.test.doubles;

import pl.smsapi.api.action.AbstractAction;

public class ActionMock extends AbstractAction {
    @Override
    protected String endPoint() {
        return null;
    }

    @Override
    protected Object createResponse(String data) {
        return null;
    }

    @Override
    protected void handleError(String text) {
    }
}