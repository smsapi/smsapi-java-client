package pl.smsapi.api.action.sender;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

/**
 * @deprecated use @link pl.smsapi.api.action.sms.sendernames.SendernameMakeDefault instead
 */
public class SenderDefault extends AbstractAction<CountableResponse> {

    public SenderDefault() {
        setJson(true);
    }

    /**
     * Set name of default sender name.
     */
    public SenderDefault setName(String sendername) {
        params.put("default", sendername);
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
