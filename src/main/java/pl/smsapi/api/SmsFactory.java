package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.sms.SMSDelete;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.proxy.Proxy;

public class SmsFactory extends ActionFactory {

    /**
     * @deprecated use {@link SmsFactory(Client, Proxy)} instead
     */
    @Deprecated
    public SmsFactory(Client client) {
        super(client);
    }

    public SmsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    /**
     * @deprecated use {@link #actionSend(String, String)} or {@link #actionSend(String[], String)} instead
     */
    @Deprecated
    public SMSSend actionSend() {
        SMSSend action = new SMSSend();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSSend actionSend(String to, String text) {
        SMSSend action = new SMSSend(to, text);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSSend actionSend(String[] to, String text) {
        SMSSend action = new SMSSend(to, text);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use {@link #actionGet(String)} instead
     */
    @Deprecated
    public SMSGet actionGet() {
        SMSGet action = new SMSGet();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSGet actionGet(String id) {
        SMSGet action = new SMSGet(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSDelete actionDelete() {
        SMSDelete action = new SMSDelete();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSDelete actionDelete(String id) {
        SMSDelete action = actionDelete();
        action.id(id);
        return action;
    }
}
