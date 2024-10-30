package pl.smsapi.api.action.sender;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.SendersResponse;

/**
 * @deprecated use @link pl.smsapi.api.action.sms.sendernames.SendernamesList instead
 */
public class SenderList extends AbstractAction<SendersResponse> {

    public SenderList() {
        setJson(true);
        params.put("list", "1");
    }

    protected SendersResponse createResponse(String data) {
        return new SendersResponse(data);
    }

    @Override
    protected String endPoint() {
        return "sender.do";
    }
}
