package pl.smsapi.api.action.user;

/**
 * @deprecated use {@link pl.smsapi.api.action.subusers.SubuserEdit()} instead
 */
@Deprecated
public class UserEdit extends AbstractUserControl<UserEdit> {

    /**
     * Set username to edit account.
     */
    public UserEdit username(String username) {
        params.put("set_user", username);
        return this;
    }
}
