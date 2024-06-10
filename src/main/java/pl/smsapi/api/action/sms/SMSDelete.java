package pl.smsapi.api.action.sms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

public class SMSDelete extends AbstractAction<CountableResponse> {

    /**
     * @deprecated use {@link SMSDelete(String)} instead
     */
    @Deprecated
    public SMSDelete() {
        setJson(true);
        id("");
    }

    public SMSDelete(String id) {
        setJson(true);
        params.put("sch_del", id);
    }

    /**
     * Set ID of message to delete.
     * <p/>
     * This id was returned after sending message.
     *
     * @deprecated use {@link SMSDelete(String)} instead
     */
    public SMSDelete id(String id) {
        params.put("sch_del", id);
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
