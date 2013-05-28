package pl.smsapi.api.action.sms;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "StatusResponse")
public class Send extends BaseAction {

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsBasicToQuery();

		query += paramsOther();

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), "/api/sms.do", query, null);
	}

	public Send() {
	}

	;
	
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

	public Send setText(String text) {
		params.put("message", text);
		return this;
	}

	public Send setDateExpire(String date) {
		params.put("expiration_date", date);
		return this;
	}

	public Send setDateExpire(long date) {
		Long time = date;
		return setDateExpire(time.toString());
	}

	public Send setDateExpire(Calendar cal) {
		long time = cal.getTimeInMillis() / 1000;
		return setDateExpire(time);
	}

	public Send setSender(String sender) {
		params.put("from", sender);
		return this;
	}

	public Send setSingle(boolean single) {
		if (single == true) {
			params.put("single", "1");
		} else if (single == false) {
			params.put("single", "0");
		}

		return this;
	}

	public Send setNoUnicode(boolean noUnicode) {
		if (noUnicode == true) {
			params.put("nounicode", "1");
		} else if (noUnicode == false) {
			params.put("nounicode", "0");
		}

		return this;
	}

	public Send setDataCoding(String dataCoding) {
		params.put("datacoding", dataCoding);
		return this;
	}

	public Send setFlash(boolean flash) {
		if (flash == true) {
			params.put("flash", "1");
		} else if (flash == false) {
			params.remove("flash");
		}

		return this;
	}

	public Send setNormalize(boolean normalize) {

		if (normalize == true) {
			params.put("normalize", "1");
		} else if (normalize == false) {
			params.remove("normalize");
		}

		return this;
	}
}
