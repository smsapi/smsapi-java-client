package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.sms.SMSDelete;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.proxy.Proxy;

public class SmsFactory extends ActionFactory {

    public SmsFactory(Client client) {
        super(client);
    }

    public SmsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public SMSSend actionSend() {
        SMSSend action = new SMSSend();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSSend actionSend(String to, String text) {
        String[] tos = new String[]{to};
        return actionSend(tos, text);
    }

    public SMSSend actionSend(String[] to, String text) {
        SMSSend action = actionSend();
        action.setTo(to);
        action.setText(text);

        return action;
    }

    public SMSGet actionGet() {
        SMSGet action = new SMSGet();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SMSGet actionGet(String id) {
        SMSGet action = actionGet();
        action.id(id);
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
