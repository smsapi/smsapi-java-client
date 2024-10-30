package pl.smsapi.api.action.sender;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

/**
 * @deprecated use @link pl.smsapi.api.action.sms.sendernames.SendernameAdd instead
 */
public class SenderAdd extends AbstractAction<CountableResponse> {

    public SenderAdd() {
        setJson(true);
    }

    /**
     * Set new sender name.
     */
    public SenderAdd setName(String sendername) {
        params.put("add", sendername);
        return this;
    }

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }

    @Override
    protected String endPoint() {
        return "sender.do";
    }
}
