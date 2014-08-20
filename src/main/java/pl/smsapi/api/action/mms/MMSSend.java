package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractSendAction;
import pl.smsapi.api.response.SendStatusResponse;

public class MMSSend extends AbstractSendAction<MMSSend, SendStatusResponse> {

    public MMSSend() {
        setJson(true);
    }

    /**
     * Set message subject.
     */
    public MMSSend setSubject(String subject) {
        params.put("subject", subject);
        return this;
    }

    /**
     * Set MMS smill.
     */
    public MMSSend setSmil(String smil) {
        params.put("smil", smil);
        return this;
    }

    protected SendStatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new SendStatusResponse(jsonObject.getInt("count"), jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "mms.do";
    }
}
