package pl.smsapi.api.action.mms;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.StatusResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

public class Send extends BaseAction<StatusResponse> {

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsBasicToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"mms.do", query, null);
	}

	public Send() {}

	public Send setTo(String to) {
		this.to.add(to);
		return this;
	}

	public Send setTo(String[] to) {
		for (String item : to) {
			setTo(item);
		}
		return this;
	}

	public Send setGroup(String group) {
		this.group = group;
		return this;
	}

	public Send setDateSent(String date) {
		this.date = date;
		return this;
	}

	public Send setDateSent(long date) {
		Long time = date;
		return setDateSent(time.toString());
	}

	public Send setDateSent(Calendar cal) {
		long time = cal.getTimeInMillis() / 1000;
		return setDateSent(time);
	}

	public Send setIDx(String idx) {
		this.idx.add(idx);
		return this;
	}

	public Send setIDx(String[] idx) {
		for (String item : idx) {
			setIDx(item);
		}
		return this;
	}

	public Send setCheckIDx(boolean check) {
		if (check == true) {
			params.put("check_idx", "1");
		} else if (check == false) {
			params.put("check_idx", "0");
		}

		return this;
	}

	public Send setPartner(String partner) {
		params.put("partner_id", partner);
		return this;
	}

	public Send setSubject(String subject) {
		params.put("subject", subject);
		return this;
	}

	public Send setSmil(String smil) {
		params.put("smil", smil);
		return this;
	}

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }
}
