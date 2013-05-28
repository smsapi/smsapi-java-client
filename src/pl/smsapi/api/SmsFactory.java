package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class SmsFactory extends ActionFactory {

	public SmsFactory(Client client) {
		super(client);
	}

	public SmsFactory(Proxy proxy, Client client) {
		super(proxy, client);
	}

	public pl.smsapi.api.action.sms.Send actionSend() {
		pl.smsapi.api.action.sms.Send action = new pl.smsapi.api.action.sms.Send();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.sms.Send actionSend(String to, String text) {
		String[] tos = new String[]{to};
		return actionSend(tos, text);
	}

	public pl.smsapi.api.action.sms.Send actionSend(String[] to, String text) {
		pl.smsapi.api.action.sms.Send action = actionSend();
		action.setTo(to);
		action.setText(text);

		return action;
	}

	public pl.smsapi.api.action.sms.Get actionGet() {
		pl.smsapi.api.action.sms.Get action = new pl.smsapi.api.action.sms.Get();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.sms.Get actionGet(String id) {
		pl.smsapi.api.action.sms.Get action = actionGet();
		action.id(id);
		return action;
	}

	public pl.smsapi.api.action.sms.Delete actionDelete() {
		pl.smsapi.api.action.sms.Delete action = new pl.smsapi.api.action.sms.Delete();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.sms.Delete actionDelete(String id) {
		pl.smsapi.api.action.sms.Delete action = actionDelete();
		action.id(id);
		return action;
	}
}
