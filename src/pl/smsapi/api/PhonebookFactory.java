package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class PhonebookFactory extends ActionFactory {

	public PhonebookFactory(Client client) {
		super(client);
	}

	public PhonebookFactory(Client client, Proxy proxy) {
		super(client, proxy);
	}

	public pl.smsapi.api.action.phonebook.GroupList actionGroupList() {
		pl.smsapi.api.action.phonebook.GroupList action = new pl.smsapi.api.action.phonebook.GroupList();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.phonebook.GroupAdd actionGroupAdd(String groupName) {
		pl.smsapi.api.action.phonebook.GroupAdd action = new pl.smsapi.api.action.phonebook.GroupAdd();
		action.client(client);
		action.proxy(proxy);
		action.setName(groupName);
		return action;
	}

	public pl.smsapi.api.action.phonebook.GroupEdit actionGroupEdit(String groupName) {
		pl.smsapi.api.action.phonebook.GroupEdit action = new pl.smsapi.api.action.phonebook.GroupEdit();
		action.client(client);
		action.proxy(proxy);
		action.setGroup(groupName);
		return action;
	}

	public pl.smsapi.api.action.phonebook.GroupGet actionGroupGet(String groupName) {
		pl.smsapi.api.action.phonebook.GroupGet action = new pl.smsapi.api.action.phonebook.GroupGet();
		action.client(client);
		action.proxy(proxy);
		action.setGroup(groupName);
		return action;
	}

	public pl.smsapi.api.action.phonebook.GroupDelete actionGroupDelete(String groupName) {
		pl.smsapi.api.action.phonebook.GroupDelete action = new pl.smsapi.api.action.phonebook.GroupDelete();
		action.client(client);
		action.proxy(proxy);
		action.setGroup(groupName);
		return action;
	}

	public pl.smsapi.api.action.phonebook.ContactList actionContactList() {
		pl.smsapi.api.action.phonebook.ContactList action = new pl.smsapi.api.action.phonebook.ContactList();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.phonebook.ContactAdd actionContactAdd(String number) {
		pl.smsapi.api.action.phonebook.ContactAdd action = new pl.smsapi.api.action.phonebook.ContactAdd();
		action.client(client);
		action.proxy(proxy);
		action.setNumber(number);
		return action;
	}

	public pl.smsapi.api.action.phonebook.ContactEdit actionContactEdit(String number) {
		pl.smsapi.api.action.phonebook.ContactEdit action = new pl.smsapi.api.action.phonebook.ContactEdit();
		action.client(client);
		action.proxy(proxy);
		action.setContact(number);
		return action;
	}

	public pl.smsapi.api.action.phonebook.ContactGet actionContactGet(String number) {
		pl.smsapi.api.action.phonebook.ContactGet action = new pl.smsapi.api.action.phonebook.ContactGet();
		action.client(client);
		action.proxy(proxy);
		action.setContact(number);
		return action;
	}

	public pl.smsapi.api.action.phonebook.ContactDelete actionContactDelete(String number) {
		pl.smsapi.api.action.phonebook.ContactDelete action = new pl.smsapi.api.action.phonebook.ContactDelete();
		action.client(client);
		action.proxy(proxy);
		action.setContact(number);
		return action;
	}
}
