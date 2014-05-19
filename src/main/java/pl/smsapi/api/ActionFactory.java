package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.proxy.Proxy;
import pl.smsapi.proxy.ProxyNative;

public abstract class ActionFactory {

    protected Client client;
    protected Proxy proxy;

    public ActionFactory() {
        this.proxy = new ProxyNative("https://ssl.smsapi.pl/");
    }

    public ActionFactory(Client client) {
        this.client = client;
        this.proxy = new ProxyNative("https://ssl.smsapi.pl/");
    }

    public ActionFactory(Client client, Proxy proxy) {
        this.proxy = proxy;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Client getClient() {
        return client;
    }

    public Proxy getProxy() {
        return proxy;
    }
}
