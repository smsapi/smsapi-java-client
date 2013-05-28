package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class MmsFactory extends ActionFactory {

	public MmsFactory(Client client) {
		super(client);
	}

	public MmsFactory(Proxy proxy, Client client) {
		super(proxy, client);
	}

	public pl.smsapi.api.action.mms.Send actionSend() {
		pl.smsapi.api.action.mms.Send action = new pl.smsapi.api.action.mms.Send();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.mms.Send actionSend(String to, String smil) {
		String[] tos = new String[]{to};
		return actionSend(tos, smil);
	}

	public pl.smsapi.api.action.mms.Send actionSend(String[] to, String smil) {
		pl.smsapi.api.action.mms.Send action = actionSend();
		action.setTo(to);
		action.setSmil(smil);

		return action;
	}

	public pl.smsapi.api.action.mms.Get actionGet() {
		pl.smsapi.api.action.mms.Get action = new pl.smsapi.api.action.mms.Get();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.mms.Get actionGet(String id) {
		pl.smsapi.api.action.mms.Get action = actionGet();
		action.id(id);
		return action;
	}

	public pl.smsapi.api.action.mms.Delete actionDelete() {
		pl.smsapi.api.action.mms.Delete action = new pl.smsapi.api.action.mms.Delete();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.mms.Delete actionDelete(String id) {
		pl.smsapi.api.action.mms.Delete action = actionDelete();
		action.id(id);
		return action;
	}
}
