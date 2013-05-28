package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class UserFactory extends ActionFactory {

	public UserFactory(Client client) {
		super(client);
	}

	public UserFactory(Proxy proxy, Client client) {
		super(proxy, client);
	}

	public pl.smsapi.api.action.user.List actionList() {
		pl.smsapi.api.action.user.List action = new pl.smsapi.api.action.user.List();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.user.Add actionAdd() {
		pl.smsapi.api.action.user.Add action = new pl.smsapi.api.action.user.Add();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.user.Edit actionEdit(String username) {
		pl.smsapi.api.action.user.Edit action = new pl.smsapi.api.action.user.Edit();
		action.client(client);
		action.proxy(proxy);
		action.setUsername(username);
		return action;
	}

	public pl.smsapi.api.action.user.GetPoints actionGetPoints() {
		pl.smsapi.api.action.user.GetPoints action = new pl.smsapi.api.action.user.GetPoints();
		action.client(client);
		action.proxy(proxy);
		return action;
	}
}
