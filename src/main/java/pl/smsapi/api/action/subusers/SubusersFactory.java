package pl.smsapi.api.action.subusers;

import pl.smsapi.Client;
import pl.smsapi.api.ActionFactory;
import pl.smsapi.proxy.Proxy;

public class SubusersFactory extends ActionFactory {

    public SubusersFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public SubuserAdd actionAdd(String username, String password) {
        SubuserAdd action = new SubuserAdd(username, password);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SubuserGet actionGet(String id) {
        SubuserGet action = new SubuserGet(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SubuserEdit actionEdit(String id) {
        SubuserEdit action = new SubuserEdit(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SubuserDelete actionDelete(String id) {
        SubuserDelete action = new SubuserDelete(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SubusersList actionList() {
        SubusersList action = new SubusersList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
