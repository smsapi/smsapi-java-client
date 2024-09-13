package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.sender.SenderAdd;
import pl.smsapi.api.action.sender.SenderDefault;
import pl.smsapi.api.action.sender.SenderDelete;
import pl.smsapi.api.action.sender.SenderList;
import pl.smsapi.proxy.Proxy;

/**
 * @deprecated use @link pl.smsapi.api.action.sms.sendernames.SendernamesFactory instead
 */
public class SenderFactory extends ActionFactory {

    /**
     * @deprecated use @link SenderFactory(Client, Proxy) instead
     */
    @Deprecated
    public SenderFactory(Client client) {
        super(client);
    }

    public SenderFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public SenderList actionList() {
        SenderList action = new SenderList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SenderAdd actionAdd(String senderName) {
        SenderAdd action = new SenderAdd();
        action.client(client);
        action.proxy(proxy);
        action.setName(senderName);
        return action;
    }

    public SenderDelete actionDelete(String senderName) {
        SenderDelete action = new SenderDelete();
        action.client(client);
        action.proxy(proxy);
        action.setSender(senderName);
        return action;
    }

    public SenderDefault actionSetDefault(String senderName) {
        SenderDefault action = new SenderDefault();
        action.client(client);
        action.proxy(proxy);
        action.setName(senderName);
        return action;
    }
}
