package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.contacts.*;
import pl.smsapi.proxy.Proxy;
import pl.smsapi.proxy.ProxyNative;

public class ContactsFactory extends ActionFactory {

    public ContactsFactory(Client client) {
        super(client);
        this.proxy = new ProxyNative("https://api.smsapi.pl/contacts/");
    }

    public ContactsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public ContactsContactList actionContactList() {
        ContactsContactList action = new ContactsContactList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactsContactGet actionContactGet(String contactId) {
        ContactsContactGet action = new ContactsContactGet();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    public ContactsContactAdd actionContactAdd() {
        ContactsContactAdd action = new ContactsContactAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactsContactDelete actionContactDelete(String contactId) {
        ContactsContactDelete action = new ContactsContactDelete();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    public ContactsContactEdit actionContactEdit(String contactId) {
        ContactsContactEdit action = new ContactsContactEdit();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    public ContactsGroupList actionGroupList() {
        ContactsGroupList action = new ContactsGroupList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactsGroupAdd actionGroupAdd() {
        ContactsGroupAdd action = new ContactsGroupAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public ContactsGroupDelete actionGroupDelete(String groupId) {
        ContactsGroupDelete action = new ContactsGroupDelete();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }

    public ContactsGroupGet actionGroupGet(String groupId) {
        ContactsGroupGet action = new ContactsGroupGet();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }

    public ContactsGroupEdit actionGroupEdit(String groupId) {
        ContactsGroupEdit action = new ContactsGroupEdit();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }
}
