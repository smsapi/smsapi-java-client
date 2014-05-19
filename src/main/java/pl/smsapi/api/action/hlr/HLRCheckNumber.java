package pl.smsapi.api.action.hlr;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.CheckNumberResponse;

public class HLRCheckNumber extends AbstractAction<CheckNumberResponse> {

    public HLRCheckNumber() {
        setJson(true);
    }


    public HLRCheckNumber setNumber(String number) {
        params.put("number", number);
        return this;
    }

	/*public HLRCheckNumber setNumber(String[] number) {
        params.put("number", StringUtils.join(number, ','));
		return this;
	}*/

    protected CheckNumberResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new CheckNumberResponse(jsonObject.getInt("count"), jsonObject.getJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "hlrsync.do";
    }
}
