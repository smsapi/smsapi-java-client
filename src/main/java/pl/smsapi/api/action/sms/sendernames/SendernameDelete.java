package pl.smsapi.api.action.sms.sendernames;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class SendernameDelete extends AbstractAction<RawResponse> {

    private final String sender;

    public SendernameDelete(String sender) {
        this.sender = sender;
    }

    @Override
    protected String endPoint() {
        return "sms/sendernames/" + sender;
    }

    @Override
    protected String httpMethod() {
        return "DELETE";
    }

    @Override
    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
