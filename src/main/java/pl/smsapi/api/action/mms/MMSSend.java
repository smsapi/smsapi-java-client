package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractSendAction;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.StatusResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

public class MMSSend extends AbstractSendAction<MMSSend, StatusResponse> {

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

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "mms.do";
    }
}
