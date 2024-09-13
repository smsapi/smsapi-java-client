package pl.smsapi.api.action.vms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.StatusResponse;

public class VMSGet extends AbstractAction<StatusResponse> {

    /**
     * @deprecated use @link VMSGet(String)} or @link VMSGet(String[]) instead
     */
    public VMSGet() {
        setJson(true);
        id("");
    }

    public VMSGet(String id) {
        setJson(true);
        params.put("status", id);
    }

    public VMSGet(String[] ids) {
        setJson(true);
        params.put("status", StringUtils.join(ids, '|'));
    }

    /**
     * Set ID of message to check.
     * This id was returned after sending message.
     *
     * @deprecated set id while constructing action, @link VMSGet(String)}
     */
    public VMSGet id(String id) {
        params.put("status", id);
        return this;
    }

    /**
     * Set IDs of messages to check.
     * This id was returned after sending message.
     *
     * @deprecated set id while constructing action, @link VMSGet(String[])}
     */
    public VMSGet ids(String[] ids) {
        params.put("status", StringUtils.join(ids, '|'));
        return this;
    }

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "vms.do";
    }
}
