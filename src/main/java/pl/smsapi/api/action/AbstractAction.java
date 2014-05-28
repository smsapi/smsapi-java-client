package pl.smsapi.api.action;

import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.Client;
import pl.smsapi.exception.*;
import pl.smsapi.proxy.Proxy;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractAction<T> {

    public final static String RESPONSE_PACKAGE_NAME = "pl.smsapi.api.response";

    protected Client client;
    protected Proxy proxy;
    protected HashMap<String, String> params = new HashMap<String, String>();
    protected HashMap<String, InputStream> files = new HashMap<String, InputStream>();

    abstract protected String endPoint();

    protected AbstractAction<T> setJson(boolean flag) {

        if (flag) {
            params.put("format", "json");
        } else {
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

    protected abstract T createResponse(String data);

    public T execute() throws SmsapiException {

        T response = null;

        try {

            params.put("username", client.getUsername());
            params.put("password", client.getPassword());

            String result = proxy.execute(endPoint(), params, files);

            handleError(result);

            response = createResponse(result);

            return response;

        } catch (SmsapiException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            throw e;
        } catch (Exception e) {
            throw new ProxyException(e.getMessage(), e);
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
            } catch (JSONException e) {
                throw new HostException("Invalid response", 999);
            }
        }
    }
}
