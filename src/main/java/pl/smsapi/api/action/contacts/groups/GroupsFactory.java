package pl.smsapi.api.action.contacts.groups;

import pl.smsapi.Client;
import pl.smsapi.api.ActionFactory;
import pl.smsapi.proxy.Proxy;

public class GroupsFactory extends ActionFactory {

    public GroupsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public GroupAdd actionAdd(String name) {
        GroupAdd action = new GroupAdd(name);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public GroupGet actionGet(String id) {
        GroupGet action = new GroupGet(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public GroupEdit actionEdit(String id) {
        GroupEdit action = new GroupEdit(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public GroupDelete actionDelete(String id) {
        GroupDelete action = new GroupDelete(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public GroupsList actionList() {
        GroupsList action = new GroupsList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
