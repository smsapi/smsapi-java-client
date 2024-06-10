package pl.smsapi.api.action.hlr;

import pl.smsapi.Client;
import pl.smsapi.api.ActionFactory;
import pl.smsapi.proxy.Proxy;

public class HlrFactory extends ActionFactory {

    public HlrFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public HLRCheckNumber actionCheckNumber(String phoneNumber) {
        HLRCheckNumber action = new HLRCheckNumber();
        action.client(client);
        action.proxy(proxy);

        action.setNumber(phoneNumber);

        return action;
    }
}
