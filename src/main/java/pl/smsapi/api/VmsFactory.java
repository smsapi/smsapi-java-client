package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.vms.VMSDelete;
import pl.smsapi.api.action.vms.VMSGet;
import pl.smsapi.api.action.vms.VMSSend;
import pl.smsapi.proxy.Proxy;

public class VmsFactory extends ActionFactory {

    public VmsFactory(Client client) {
        super(client);
    }

    public VmsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public VMSSend actionSend() {
        VMSSend action = new VMSSend();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSSend actionSend(String to, String tts) {
        String[] tos = new String[]{to};
        return actionSend(tos, tts);
    }

    public VMSSend actionSend(String[] to, String tts) {
        VMSSend action = actionSend();
        action.setTo(to);
        action.setTts(tts);

        return action;
    }

    public VMSGet actionGet() {
        VMSGet action = new VMSGet();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSGet actionGet(String id) {
        VMSGet action = actionGet();
        action.id(id);
        return action;
    }

    public VMSDelete actionDelete() {
        VMSDelete action = new VMSDelete();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSDelete actionDelete(String id) {
        VMSDelete action = actionDelete();
        action.id(id);
        return action;
    }
}
