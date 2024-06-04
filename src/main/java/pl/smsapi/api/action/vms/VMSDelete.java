package pl.smsapi.api.action.vms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

public class VMSDelete extends AbstractAction<CountableResponse> {

    /**
     * @deprecated use {@link VMSDelete(String)} or {@link VMSDelete(String[])} instead
     */
    public VMSDelete() {
        setJson(true);
        id("");
    }

    public VMSDelete(String id) {
        setJson(true);
        params.put("sch_del", id);
    }

    public VMSDelete(String[] ids) {
        setJson(true);
        params.put("sch_del", StringUtils.join(ids, '|'));
    }

    /**
     * Set ID of message to delete.
     * <p/>
     * This id was returned after sending message.
     *
     * @deprecated set id while constructing action, {@link VMSDelete(String)}
     */
    public VMSDelete id(String id) {
        params.put("sch_del", id);
        return this;
    }

    /**
     * Set ID of message to delete.
     * <p/>
     * This id was returned after sending message.
     *
     * @deprecated set ids while constructing action, {@link VMSDelete(String[])}
     */
    public VMSDelete ids(String[] ids) {
        params.put("sch_del", StringUtils.join(ids, '|'));
        return this;
    }

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }

    @Override
    protected String endPoint() {
        return "vms.do";
    }
}
