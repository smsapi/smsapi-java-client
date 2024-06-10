package pl.smsapi.api.action.hlr;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CheckNumberResponse;

public class HLRCheckNumber extends AbstractAction<CheckNumberResponse> {

    /**
     * @deprecated use {@link HLRCheckNumber(String)} instead
     */
    @Deprecated
    public HLRCheckNumber() {
        setJson(true);
    }

    public HLRCheckNumber(String phoneNumber) {
        setJson(true);
        params.put("number", phoneNumber);
    }

    /**
     * @deprecated use {@link HLRCheckNumber(String)} instead
     */
    @Deprecated
    public HLRCheckNumber setNumber(String number) {
        params.put("number", number);
        return this;
    }

    protected CheckNumberResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CheckNumberResponse(jsonObject.getInt("count"), jsonObject.getJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "hlrsync.do";
    }
}
