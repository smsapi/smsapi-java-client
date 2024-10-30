package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.vms.VMSDelete;
import pl.smsapi.api.action.vms.VMSGet;
import pl.smsapi.api.action.vms.VMSSend;
import pl.smsapi.proxy.Proxy;

import java.io.File;
import java.io.IOException;

public class VmsFactory extends ActionFactory {

    /**
     * @deprecated use @link VmsFactory(Client, Proxy) instead
     */
    @Deprecated
    public VmsFactory(Client client) {
        super(client);
    }

    public VmsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    /**
     * @deprecated use @link #actionSend(String, String)} or @link #actionSend(String[], String)}
     * or @link #actionSend(String, File)} or @link #actionSend(String[], File) instead
     */
    public VMSSend actionSend() {
        VMSSend action = new VMSSend();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSSend actionSend(String to, String tts) {
        VMSSend action = new VMSSend(to, tts);
        action.client(client);
        action.proxy(proxy);

        return action;
    }

    public VMSSend actionSend(String[] to, String tts) {
        VMSSend action = new VMSSend(to, tts);
        action.client(client);
        action.proxy(proxy);

        return action;
    }

    public VMSSend actionSend(String to, File file) throws IOException {
        VMSSend action = new VMSSend(to, file);
        action.client(client);
        action.proxy(proxy);

        return action;
    }

    public VMSSend actionSend(String[] to, File file) throws IOException {
        VMSSend action = new VMSSend(to, file);
        action.client(client);
        action.proxy(proxy);

        return action;
    }

    /**
     * @deprecated use @link #actionGet(String) instead
     */
    public VMSGet actionGet() {
        VMSGet action = new VMSGet();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSGet actionGet(String id) {
        VMSGet action = new VMSGet(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link #actionDelete(String) instead
     */
    public VMSDelete actionDelete() {
        VMSDelete action = new VMSDelete();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public VMSDelete actionDelete(String id) {
        VMSDelete action = new VMSDelete(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
