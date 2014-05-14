package pl.smsapi.api.action.user;

public class UserEdit extends AbstractUserControl<UserEdit> {

    /**
     * Set username to edit account.
     */
    public UserEdit username(String username) {
        params.put("set_user", username);
        return this;
    }
}
