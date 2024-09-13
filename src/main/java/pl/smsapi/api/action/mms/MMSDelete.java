package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CountableResponse;

public class MMSDelete extends AbstractAction<CountableResponse> {

    public MMSDelete() {
        setJson(true);
        id("");
    }

    /**
     * Set ID of message to delete.
     * This id was returned after sending message.
     */
    public MMSDelete id(String id) {
        params.put("sch_del", id);
        return this;
    }

    /**
     * Set ID of message to delete.
     * This id was returned after sending message.
     */
    public MMSDelete ids(String[] ids) {
        params.put("sch_del", StringUtils.join(ids, '|'));
        return this;
    }

    protected CountableResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CountableResponse(jsonObject.getInt("count"));
    }

    @Override
    protected String endPoint() {
        return "mms.do";
    }
}
