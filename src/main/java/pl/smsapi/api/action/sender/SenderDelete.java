package pl.smsapi.api.action.sender;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

/**
 * @deprecated use {@link pl.smsapi.api.action.sms.sendernames.SendernameDelete} instead
 */
public class SenderDelete extends AbstractAction<CountableResponse> {

    public SenderDelete() {
        setJson(true);
    }

    /**
     * Set sender name to delete.
     */
    public SenderDelete setSender(String sendername) {
        params.put("delete", sendername);
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
