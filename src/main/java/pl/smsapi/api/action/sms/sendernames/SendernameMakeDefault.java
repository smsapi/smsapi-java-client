package pl.smsapi.api.action.sms.sendernames;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class SendernameMakeDefault extends AbstractAction<RawResponse> {

    private final String sender;

    public SendernameMakeDefault(String sender) {
        this.sender = sender;
    }

    @Override
    protected String endPoint() {
        return "sms/sendernames/" + sender + "/commands/make_default";
    }

    @Override
    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
