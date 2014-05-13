package pl.smsapi.api.action;

import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.Client;
import pl.smsapi.exception.ActionException;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.HostException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.Proxy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseAction<T> {

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

    public BaseAction<T> setTest(boolean test) {
        if (test == true) {
            params.put("test", "1");
        } else if (test == false) {
            params.remove("test");
        }

        return this;
    }

    protected BaseAction<T> setJson(boolean test) {
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

    protected abstract T createResponse(String data);

    public T execute() throws SmsapiException {

        T response = null;

        try {

            setJson(true);

            String text = proxy.execute(this);
            handleError(text);
            response = createResponse(text);

            return response;

        } catch (SmsapiException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    protected void handleError(String text) throws SmsapiException {

        Matcher matcher = Pattern.compile("^ERROR:(.*)").matcher(text);

        if (matcher.find()) {
            throw new HostException("Invalid response", 999);
        } else {

            try {
                JSONObject error = new JSONObject(text);

                int errorCode = error.optInt("error");
                if (errorCode != 0) {
                    String errorMessage = error.optString("message");

                    if (SmsapiException.isHostError(errorCode)) {
                        throw new HostException(errorMessage, errorCode);
                    }

                    if (SmsapiException.isClientError(errorCode)) {
                        throw new ClientException(errorMessage, errorCode);
                    } else {
                        throw new ActionException(errorMessage, errorCode);
                    }
                }
            } catch( JSONException e ) {
            }
        }
    }
}
