package pl.smsapi.api.action.user;

public class UserAdd extends AbstractUserControl<UserAdd> {

    /**
     * New account user name.
     */
    public UserAdd setUsername(String username) {
        params.put("add_user", username);
        return this;
    }
}
