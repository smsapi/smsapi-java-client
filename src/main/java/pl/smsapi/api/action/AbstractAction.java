package pl.smsapi.api.action;

import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.Client;
import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.api.response.Response;
import pl.smsapi.exception.*;
import pl.smsapi.proxy.Proxy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractAction<T extends Response> {

    public final static String RESPONSE_PACKAGE_NAME = "pl.smsapi.api.response";

    protected Client client;
    protected Proxy proxy;
    protected HashMap<String, String> params = new HashMap<String, String>();
    protected HashMap<String, InputStream> files = new HashMap<String, InputStream>();

    abstract protected String endPoint();

    protected String httpMethod() {
        return "POST";
    }

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
            AuthenticationStrategy authenticationStrategy = client.getAuthenticationStrategy();

            String result = proxy.execute(endPoint(), params, files, httpMethod(), authenticationStrategy);

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

    protected void handleError(String text) throws SmsapiErrorException, SmsapiLegacyErrorException {

        Matcher matcher = Pattern.compile("^ERROR:(.*)").matcher(text);

        if (matcher.find()) {
            handleExtraLegacyError();
        } else {

            try {
                JSONObject error = new JSONObject(text);
                int legacyErrorCode = error.optInt("error");
                if (legacyErrorCode != 0) {
                    handleLegacyError(error, legacyErrorCode);
                } else {
                    String errorCode = error.optString("error", null);
                    if (errorCode != null) {
                        throw new SmsapiErrorException(error.optString("message"), errorCode);
                    }
                }
            } catch (JSONException nonJsonSuccessResponse) {
            }
        }
    }

    private static void handleLegacyError(JSONObject error, int errorCode) throws SmsapiLegacyErrorException {
        String errorMessage = error.optString("message");

        if (SmsapiLegacyErrorException.isHostError(errorCode)) {
            throw new HostException(errorMessage, errorCode);
        }

        if (SmsapiLegacyErrorException.isClientError(errorCode)) {
            throw new ClientException(errorMessage, errorCode);
        } else {
            throw new ActionException(errorMessage, errorCode);
        }
    }

    private static void handleExtraLegacyError() throws HostException {
        throw new HostException("Invalid response", 999);
    }
}
