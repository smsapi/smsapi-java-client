package pl.smsapi.api.action.user;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.UserResponse;

/**
 * @deprecated use {@link pl.smsapi.api.action.subusers.SubuserGet()} instead
 */
@Deprecated
public class UserGet extends AbstractAction<UserResponse> {

    public UserGet() {
        setJson(true);
    }

    /**
     * Set username to get account.
     */
    public UserGet username(String username) {
        params.put("get_user", username);
        return this;
    }

    protected UserResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return
                new UserResponse(
                        jsonObject.getString("username"),
                        jsonObject.optDouble("limit"),
                        jsonObject.optDouble("month_limit"),
                        jsonObject.optInt("senders"),
                        jsonObject.optInt("phonebook"),
                        jsonObject.optInt("active"),
                        jsonObject.optString("info")
                );
    }

    @Override
    protected String endPoint() {
        return "user.do";
    }
}
