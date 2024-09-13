package pl.smsapi.api;

import pl.smsapi.Client;
import pl.smsapi.api.action.subusers.SubusersFactory;
import pl.smsapi.api.action.user.UserAdd;
import pl.smsapi.api.action.user.UserEdit;
import pl.smsapi.api.action.user.UserGetPoints;
import pl.smsapi.api.action.user.UserList;
import pl.smsapi.proxy.Proxy;

public class UserFactory extends ActionFactory {

    /**
     * @deprecated use @link UserFactory(Client, Proxy) instead
     */
    @Deprecated
    public UserFactory(Client client) {
        super(client);
    }

    public UserFactory(Client client, Proxy proxy) {
        super(client, proxy);
    }

    /**
     * @deprecated use @link SubusersFactory#actionList() instead
     */
    @Deprecated
    public UserList actionList() {
        UserList action = new UserList();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link SubusersFactory#actionAdd(String, String)} () instead
     */
    @Deprecated
    public UserAdd actionAdd() {
        UserAdd action = new UserAdd();
        action.client(client);
        action.proxy(proxy);
        return action;
    }

    /**
     * @deprecated use @link SubusersFactory#actionEdit(String)} () instead
     */
    @Deprecated
    public UserEdit actionEdit(String username) {
        UserEdit action = new UserEdit();
        action.client(client);
        action.proxy(proxy);
        action.username(username);
        return action;
    }

    public UserGetPoints actionGetPoints() {
        UserGetPoints action = new UserGetPoints();
        action.client(client);
        action.proxy(proxy);
        return action;
    }
}
