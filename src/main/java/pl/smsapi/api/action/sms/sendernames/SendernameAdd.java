package pl.smsapi.api.action.sms.sendernames;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SendernameAdd extends AbstractAction<Sendername> {

    public SendernameAdd(String sender) {
        params.put("sender", sender);
    }

    @Override
    protected String endPoint() {
        return "sms/sendernames";
    }

    @Override
    protected Sendername createResponse(String data) {
        return new Sendername.SendernameFactory().createFrom(new JSONObject(data));
    }
}
