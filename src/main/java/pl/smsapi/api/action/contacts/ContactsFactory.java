package pl.smsapi.api.action.contacts;

import pl.smsapi.Client;
import pl.smsapi.api.ActionFactory;
import pl.smsapi.proxy.Proxy;

public class ContactsFactory extends ActionFactory {

    public ContactsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public ContactAdd actionAdd() {
        ContactAdd action = new ContactAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactGet actionGet(String id) {
        ContactGet action = new ContactGet(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactEdit actionEdit(String id) {
        ContactEdit action = new ContactEdit(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactDelete actionDelete(String id) {
        ContactDelete action = new ContactDelete(id);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactsList actionList() {
        ContactsList action = new ContactsList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
