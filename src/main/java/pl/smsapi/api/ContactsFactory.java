package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.contacts.*;
import pl.smsapi.api.action.contacts.groups.GroupsFactory;
import pl.smsapi.proxy.Proxy;
import pl.smsapi.proxy.ProxyNative;

/**
 * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory() or @link pl.smsapi.api.action.contacts.groups.GroupsFactory() instead
 */
@Deprecated
public class ContactsFactory extends ActionFactory {

    /**
     * @deprecated use @link ContactsFactory(Client, Proxy) or @link pl.smsapi.api.action.contacts.ContactsFactory(Client, Proxy) instead
     */
    @Deprecated
    public ContactsFactory(Client client) {
        super(client);
        this.proxy = new ProxyNative("https://api.smsapi.pl/contacts/");
    }

    public ContactsFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    /**
     * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory#actionList() instead
     */
    @Deprecated
    public ContactsContactList actionContactList() {
        ContactsContactList action = new ContactsContactList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory#actionGet(String) instead
     */
    @Deprecated
    public ContactsContactGet actionContactGet(String contactId) {
        ContactsContactGet action = new ContactsContactGet();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    /**
     * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory#actionAdd() instead
     */
    @Deprecated
    public ContactsContactAdd actionContactAdd() {
        ContactsContactAdd action = new ContactsContactAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory#actionDelete(String) instead
     */
    @Deprecated
    public ContactsContactDelete actionContactDelete(String contactId) {
        ContactsContactDelete action = new ContactsContactDelete();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    /**
     * @deprecated use @link pl.smsapi.api.action.contacts.ContactsFactory#actionEdit(String) instead
     */
    @Deprecated
    public ContactsContactEdit actionContactEdit(String contactId) {
        ContactsContactEdit action = new ContactsContactEdit();
        action.client(client);
        action.proxy(proxy);
        action.contactId(contactId);
        return action;
    }

    /**
     * @deprecated use @link GroupsFactory#actionList() instead
     */
    @Deprecated
    public ContactsGroupList actionGroupList() {
        ContactsGroupList action = new ContactsGroupList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link GroupsFactory#actionAdd(String)} () instead
     */
    @Deprecated
    public ContactsGroupAdd actionGroupAdd() {
        ContactsGroupAdd action = new ContactsGroupAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link GroupsFactory#actionDelete(String)} () instead
     */
    @Deprecated
    public ContactsGroupDelete actionGroupDelete(String groupId) {
        ContactsGroupDelete action = new ContactsGroupDelete();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }

    /**
     * @deprecated use @link GroupsFactory#actionGet(String)} () instead
     */
    @Deprecated
    public ContactsGroupGet actionGroupGet(String groupId) {
        ContactsGroupGet action = new ContactsGroupGet();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }

    /**
     * @deprecated use @link GroupsFactory#actionEdit(String)} () instead
     */
    @Deprecated
    public ContactsGroupEdit actionGroupEdit(String groupId) {
        ContactsGroupEdit action = new ContactsGroupEdit();
        action.client(client);
        action.proxy(proxy);
        action.groupId(groupId);
        return action;
    }
}
