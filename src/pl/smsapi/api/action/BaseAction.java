package pl.smsapi.api.action;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import pl.smsapi.Client;
import pl.smsapi.api.response.*;
import pl.smsapi.exception.*;
import pl.smsapi.proxy.Proxy;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class BaseAction {

	public final static String RESPONSE_PACKAGE_NAME = "pl.smsapi.api.response";
	protected Client client;
	protected Proxy proxy;
	protected final HashMap<String, String> params = new HashMap<String, String>();
	protected final ArrayList<String> to = new ArrayList<String>();
	protected final ArrayList<String> idx = new ArrayList<String>();
	protected String group;
	protected String date;

	abstract public URI uri() throws URISyntaxException;

	public File file() {
		return null;
	}

    protected String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

	public BaseAction setTest(boolean test) {
		if (test == true) {
			params.put("test", "1");
		} else if (test == false) {
			params.remove("test");
		}

		return this;
	}

	public BaseAction setJson(boolean test) {
		if (test == true) {
			params.put("format", "json");
		} else if (test == false) {
			params.remove("json");
		}

		return this;
	}

	public void client(Client client) {
		this.client = client;
	}

	public void proxy(Proxy proxy) {
		this.proxy = proxy;
	}

	protected String paramsOther() {
		return paramsOther("");
	}

	protected String paramsOther(String skip) {

		String query = "";
		Set set = params.entrySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();

			if (!skip.equals(me.getKey())) {
				if (me.getValue() != null) {
					query += "&" + me.getKey() + "=" + urlEncodeUTF8( me.getValue().toString() );
				}
			}
		}
		return query;
	}

	protected String join(String r[], String d) {
		if (r.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < r.length - 1; i++) {
			sb.append(r[i] + d);
		}

		return sb.toString() + r[i];
	}

	protected String renderTo() {

		class ListToAndIdx {

			ArrayList<String> to;
			ArrayList<String> idx;
			int sizeTo;
			int sizeIdx;
			String queryTo;
			String queryIdx;
			String error;

			ListToAndIdx(ArrayList<String> to, ArrayList<String> idx) {

				this.to = to;
				this.idx = idx;
			}

			private String renderList(ArrayList<String> list, String delimiter) {
				String query = "";
				int loop = 1;
				int size = list.size();

				for (String item : list) {
					query += item;
					if (loop < size) {
						query += delimiter;
					}

					loop++;
				}

				return query;
			}

			private String renderListTo() {
				return renderList(to, ",");
			}

			private String renderListIdx() {
				return renderList(idx, "|");
			}

			public String getError() {
				return error;
			}

			@Override
			public String toString() {
				int sizeTo = to.size();
				int sizeIdx = idx.size();

				if (sizeIdx > 0) {
					if ((sizeTo != sizeIdx)) {
						error = "size idx is not equals to";
						throw new IllegalArgumentException(error);
					} else {
						return renderListTo() + "&idx=" + renderListIdx();
					}
				}

				return renderListTo();
			}
		}

		return new ListToAndIdx(to, idx).toString();
	}

	protected String paramsBasicToQuery() {

		String query = "";

		query += (group != null) ? "&group=" + group : "&to=" + renderTo();

		query += (date != null) ? "&date=" + date : "";

		return query;
	}

	protected String paramsLoginToQuery() {
		return "username=" + client.getUsername() + "&password=" + client.getPassword();
	}

	public Response execute() throws ActionException {
		String data = null;
		Response response = null;
		String name = null;

		try {

			setJson(true);

			Annotation annotation = this.getClass().getAnnotation(ActionResponse.class);

			if (annotation instanceof ActionResponse) {
				ActionResponse myAnnotation = (ActionResponse) annotation;
				name = myAnnotation.object();
			}

			data = proxy.execute(this);

			handleError(data);

			response = new ResponseToObject(name, data).parse();

			return response;

		} catch (Exception ex) {
			Logger.getLogger(BaseAction.class.getName()).log(Level.SEVERE, null, ex);
			throw new ActionException(ex.getMessage());
		}

	}

	protected void handleError(String data) throws SmsapiException {

		ErrorResponse error = (ErrorResponse) new ResponseToObject(ErrorResponse.class, data).parse();

		if (error.code != 0) {
			if (SmsapiException.isHostError(error.code)) {
				throw new HostException(error.message, error.code);
			}

			if (SmsapiException.isClientError(error.code)) {
				throw new ClientException(error.message, error.code);
			} else {
				throw new ActionException(error.message, error.code);
			}

		}
	}

	private class ResponseToObject {

		protected Class classRes;
		protected String data;

		public ResponseToObject(String clazz, String data) {
			try {
				this.classRes = Class.forName(RESPONSE_PACKAGE_NAME + "." + clazz);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(BaseAction.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.data = data;
		}

		public ResponseToObject(Class cla, String data) {
			this.classRes = cla;
			this.data = data;
		}

		public Response parse() {

			JSONObject oData;

			if (classRes.hashCode() == ErrorResponse.class.hashCode()) {
				final String comp = data.substring(0, 1);

				if (comp.equals("[") == true) {
					oData = new JSONObject();
				} else {
					oData = new JSONObject(data);
				}


				return new ErrorResponse(oData.optString("message"), oData.optInt("error"));
			} else if (classRes.hashCode() == StatusResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new StatusResponse(oData.optInt("count"), oData.optString("list"));
			} else if (classRes.hashCode() == CountableResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new CountableResponse(oData.optInt("count"));
			} else if (classRes.hashCode() == CheckNumberResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new CheckNumberResponse(oData.optInt("count"), oData.optString("list"));
			} else if (classRes.hashCode() == UserResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new UserResponse(oData.optString("username"), oData.optString("limit"), oData.optString("month_limit"), oData.optInt("senders"), oData.optInt("phonebook"), oData.optInt("active"), oData.optString("info"));
			} else if (classRes.hashCode() == UsersResponse.class.hashCode()) {
				return new UsersResponse(data);
			} else if (classRes.hashCode() == PointsResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new PointsResponse(oData.optString("points"));
			} else if (classRes.hashCode() == SenderResponse.class.hashCode()) {
				oData = new JSONObject(data);
				new SenderResponse(oData.optString("sender"), oData.optString("status"), oData.optString("default"));
			} else if (classRes.hashCode() == SendersResponse.class.hashCode()) {
				return new SendersResponse(data);
			} else if (classRes.hashCode() == GroupResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new GroupResponse(oData.optString("name"), oData.optString("info"), oData.optInt("numbers_count"));
			} else if (classRes.hashCode() == GroupsResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new GroupsResponse(oData.optInt("count"), oData.optString("list"));
			} else if (classRes.hashCode() == ContactsResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new ContactsResponse(oData.optInt("count"), oData.optString("list"));
			} else if (classRes.hashCode() == ContactResponse.class.hashCode()) {
				oData = new JSONObject(data);
				return new ContactResponse(oData.optString("number"), oData.optString("first_name"), oData.optString("last_name"), oData.optString("info"), oData.optString("birthday"), oData.optString("city"), oData.optString("gender"), oData.optInt("date_add"), oData.optInt("date_mod"));
			} else if (classRes.hashCode() == RawResponse.class.hashCode()) {
				return new RawResponse(data);
			}

			return null;
		}
	}
}
