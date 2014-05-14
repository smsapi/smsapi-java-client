package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.mms.MMSDelete;
import pl.smsapi.api.action.mms.MMSGet;
import pl.smsapi.api.action.mms.MMSSend;
import pl.smsapi.proxy.Proxy;

public class MmsFactory extends ActionFactory {

	public MmsFactory(Client client) {
		super(client);
	}

	public MmsFactory(Client client, Proxy proxy) {
		super(client, proxy);
	}

	public MMSSend actionSend() {
		MMSSend action = new MMSSend();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public MMSSend actionSend(String to, String smil) {
		String[] tos = new String[]{to};
		return actionSend(tos, smil);
	}

	public MMSSend actionSend(String[] to, String smil) {
		MMSSend action = actionSend();
		action.setTo(to);
		action.setSmil(smil);

		return action;
	}

	public MMSGet actionGet() {
		MMSGet action = new MMSGet();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public MMSGet actionGet(String id) {
		MMSGet action = actionGet();
		action.id(id);
		return action;
	}

	public MMSDelete actionDelete() {
		MMSDelete action = new MMSDelete();
		action.client(client);
		action.proxy(proxy);
		return action;
	}

	public MMSDelete actionDelete(String id) {
		MMSDelete action = actionDelete();
		action.id(id);
		return action;
	}
}
