package pl.smsapi.api.action.vms;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.proxy.ProxyHttp;

public class Send extends BaseAction<StatusResponse> {

	public static enum Lector {

		AGNIESZKA,
		EWA,
		JACEK,
		JAN,
		MAJA;

		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	};
	private File file;
	private String tts;

	@Override
	public URI uri() throws URISyntaxException {

		String query = "";

		query += paramsLoginToQuery();

		query += paramsBasicToQuery();

		query += paramsOther();

		if (file != null) {
			if (proxy instanceof ProxyHttp) {
				ProxyHttp tmpProxy = (ProxyHttp) proxy;
				tmpProxy.getRequestMethod(ProxyHttp.RequestMethod.POST);
			}
		} else if (tts != null) {
			query += "&tts=" + tts;
		}

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"vms.do", query, null);
	}

	@Override
	public File file() {
		return file;
	}

	public Send() { }

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

	public Send setFile(File file) {
		this.file = file;
		return this;
	}

	public void setFile(String pathFile) {
		File file = new File(pathFile);
		this.file = file;
	}

	public Send setTts(String tts) {
		this.tts = tts;
		return this;
	}

	public Send setSkipGsm(boolean skipGsm) {

		if (skipGsm == true) {
			params.put("skip_gsm", "1");
		} else if (skipGsm == false) {
			params.remove("skip_gsm");
		}

		return this;
	}

	public Send setTtsLector(Lector lector) {
		params.put("tts_lector", lector.toString());
		return this;
	}

    public Send setInterval(Integer interval) {

        if( interval == null ) {
            params.remove("interval");
        } else {
            params.put("interval", interval.toString());
        }
        return this;
    }


	public Send setFrom(String from) {
		params.put("from", from);
		return this;
	}

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }
}
