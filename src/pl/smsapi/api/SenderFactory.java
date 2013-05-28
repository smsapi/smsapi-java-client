package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class SenderFactory extends ActionFactory {

	public SenderFactory(Client client) {
		super(client);
	}

	public SenderFactory(Proxy proxy, Client client) {
		super(proxy, client);
	}

	public pl.smsapi.api.action.sender.List actionList() {
		pl.smsapi.api.action.sender.List action = new pl.smsapi.api.action.sender.List();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.sender.Add actionAdd(String senderName) {
		pl.smsapi.api.action.sender.Add action = new pl.smsapi.api.action.sender.Add();
		action.client(client);
		action.proxy(proxy);
		action.setName(senderName);
		return action;
	}

	public pl.smsapi.api.action.sender.Delete actionDelete(String senderName) {
		pl.smsapi.api.action.sender.Delete action = new pl.smsapi.api.action.sender.Delete();
		action.client(client);
		action.proxy(proxy);
		action.setSender(senderName);
		return action;
	}

	public pl.smsapi.api.action.sender.Default actionSetDefault(String senderName) {
		pl.smsapi.api.action.sender.Default action = new pl.smsapi.api.action.sender.Default();
		action.client(client);
		action.proxy(proxy);
		action.setName(senderName);
		return action;
	}
}
