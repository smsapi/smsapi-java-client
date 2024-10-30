package pl.smsapi.api.action.sms.sendernames;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SendernamesList extends AbstractAction<Sendernames> {
    @Override
    protected String endPoint() {
        return "sms/sendernames";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Sendernames createResponse(String data) {
        return new Sendernames.SendernamesFromJsonFactory().createFrom(new JSONObject(data));
    }
}
