package pl.smsapi.api.action.user;

/**
 * @deprecated use @link pl.smsapi.api.action.subusers.SubuserAdd() instead
 */
@Deprecated
public class UserAdd extends AbstractUserControl<UserAdd> {

    /**
     * New account user name.
     */
    public UserAdd setUsername(String username) {
        params.put("add_user", username);
        return this;
    }
}
