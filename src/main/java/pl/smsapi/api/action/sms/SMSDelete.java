package pl.smsapi.api.action.sms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

public class SMSDelete extends AbstractAction<CountableResponse> {

    public SMSDelete() {
        setJson(true);
        id("");
    }

    /**
     * Set ID of message to delete.
     * <p/>
     * This id was returned after sending message.
     */
    public SMSDelete id(String id) {
        params.put("sch_del", id);
        return this;
    }

    /**
     * Set ID of message to delete.
     * <p/>
     * This id was returned after sending message.
     */
    public SMSDelete ids(String[] ids) {
        params.put("sch_del", StringUtils.join(ids, '|'));
        return this;
    }

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }

    @Override
    protected String endPoint() {
        return "sms.do";
    }
}
