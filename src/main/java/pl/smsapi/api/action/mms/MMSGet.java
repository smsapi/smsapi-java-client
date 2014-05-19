package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.StatusResponse;

public class MMSGet extends AbstractAction<StatusResponse> {

    public MMSGet() {
        setJson(true);
        id("");
    }

    /**
     * Set ID of message to check.
     * <p/>
     * This id was returned after sending message.
     */
    public MMSGet id(String id) {
        params.put("status", id);
        return this;
    }

    /**
     * Set IDs of messages to check.
     * <p/>
     * This id was returned after sending message.
     */
    public MMSGet ids(String[] ids) {
        params.put("status", StringUtils.join(ids, '|'));
        return this;
    }

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "mms.do";
    }
}
