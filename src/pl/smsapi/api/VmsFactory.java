package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;

public class VmsFactory extends ActionFactory {

	public VmsFactory(Client client) {
		super(client);
	}

	public VmsFactory(Client client, Proxy proxy) {
		super(client, proxy);
	}

	public pl.smsapi.api.action.vms.Send actionSend() {
		pl.smsapi.api.action.vms.Send action = new pl.smsapi.api.action.vms.Send();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.vms.Send actionSend(String to, String tts) {
		String[] tos = new String[]{to};
		return actionSend(tos, tts);
	}

	public pl.smsapi.api.action.vms.Send actionSend(String[] to, String tts) {
		pl.smsapi.api.action.vms.Send action = actionSend();
		action.setTo(to);
		action.setTts(tts);

		return action;
	}

	public pl.smsapi.api.action.vms.Get actionGet() {
		pl.smsapi.api.action.vms.Get action = new pl.smsapi.api.action.vms.Get();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.vms.Get actionGet(String id) {
		pl.smsapi.api.action.vms.Get action = actionGet();
		action.id(id);
		return action;
	}

	public pl.smsapi.api.action.vms.Delete actionDelete() {
		pl.smsapi.api.action.vms.Delete action = new pl.smsapi.api.action.vms.Delete();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public pl.smsapi.api.action.vms.Delete actionDelete(String id) {
		pl.smsapi.api.action.vms.Delete action = actionDelete();
		action.id(id);
		return action;
	}
}
