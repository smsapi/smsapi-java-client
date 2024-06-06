package pl.smsapi.api.action.sms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.StatusResponse;

public class SMSGet extends AbstractAction<StatusResponse> {

    /**
     * @deprecated use {@link SMSGet(String)} or {@link SMSGet(String[])} instead
     */
    @Deprecated
    public SMSGet() {
        setJson(true);
        id("");
    }

    public SMSGet(String id) {
        setJson(true);
        params.put("status", id);
    }

    public SMSGet(String[] ids) {
        setJson(true);
        params.put("status", StringUtils.join(ids, '|'));
    }

    /**
     * Set ID of message to check.
     * <p/>
     * This id was returned after sending message.
     *
     * @deprecated use {@link SMSGet(String)} instead
     */
    @Deprecated
    public SMSGet id(String id) {
        params.put("status", id);
        return this;
    }

    /**
     * Set IDs of messages to check.
     * <p/>
     * This id was returned after sending message.
     *
     * @deprecated use {@link SMSGet(String[])} instead
     */
    @Deprecated
    public SMSGet ids(String[] ids) {
        params.put("status", StringUtils.join(ids, '|'));
        return this;
    }

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "sms.do";
    }
}
