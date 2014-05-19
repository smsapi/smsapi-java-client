package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.phonebook.*;
import pl.smsapi.proxy.Proxy;

public class PhonebookFactory extends ActionFactory {

    public PhonebookFactory(Client client) {
        super(client);
    }

    public PhonebookFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public PhonebookGroupList actionGroupList() {
        PhonebookGroupList action = new PhonebookGroupList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public PhonebookGroupAdd actionGroupAdd(String groupName) {
        PhonebookGroupAdd action = new PhonebookGroupAdd();
        action.client(client);
        action.proxy(proxy);
        action.setName(groupName);
        return action;
    }

    public PhonebookGroupEdit actionGroupEdit(String groupName) {
        PhonebookGroupEdit action = new PhonebookGroupEdit();
        action.client(client);
        action.proxy(proxy);
        action.name(groupName);
        return action;
    }

    public PhonebookGroupGet actionGroupGet(String groupName) {
        PhonebookGroupGet action = new PhonebookGroupGet();
        action.client(client);
        action.proxy(proxy);
        action.name(groupName);
        return action;
    }

    public PhonebookGroupDelete actionGroupDelete(String groupName) {
        PhonebookGroupDelete action = new PhonebookGroupDelete();
        action.client(client);
        action.proxy(proxy);
        action.name(groupName);
        return action;
    }

    public PhonebookContactList actionContactList() {
        PhonebookContactList action = new PhonebookContactList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public PhonebookContactAdd actionContactAdd(String number) {
        PhonebookContactAdd action = new PhonebookContactAdd();
        action.client(client);
        action.proxy(proxy);
        action.setNumber(number);
        return action;
    }

    public PhonebookContactEdit actionContactEdit(String number) {
        PhonebookContactEdit action = new PhonebookContactEdit();
        action.client(client);
        action.proxy(proxy);
        action.number(number);
        return action;
    }

    public PhonebookContactGet actionContactGet(String number) {
        PhonebookContactGet action = new PhonebookContactGet();
        action.client(client);
        action.proxy(proxy);
        action.number(number);
        return action;
    }

    public PhonebookContactDelete actionContactDelete(String number) {
        PhonebookContactDelete action = new PhonebookContactDelete();
        action.client(client);
        action.proxy(proxy);
        action.number(number);
        return action;
    }
}
