package pl.smsapi.api.action.sms.sendernames;

import pl.smsapi.Client;
import pl.smsapi.api.ActionFactory;
import pl.smsapi.proxy.Proxy;

public class SendernamesFactory extends ActionFactory {

    public SendernamesFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    public SendernameAdd actionAdd(String sender) {
        SendernameAdd action = new SendernameAdd(sender);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SendernameDelete actionDelete(String sender) {
        SendernameDelete action = new SendernameDelete(sender);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SendernameMakeDefault actionMakeDefault(String sender) {
        SendernameMakeDefault action = new SendernameMakeDefault(sender);
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    public SendernamesList actionList() {
        SendernamesList action = new SendernamesList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
