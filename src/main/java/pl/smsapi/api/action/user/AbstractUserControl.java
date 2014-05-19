package pl.smsapi.api.action.user;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.UserResponse;

public abstract class AbstractUserControl<T> extends AbstractAction<UserResponse> {

    public AbstractUserControl() {
        setJson(true);
    }

    /**
     * Set account password encoded md5 algorithm.
     */
    public T setPassword(String password) {
        params.put("pass", password);
        return (T) this;
    }

    /**
     * Set account api password hashed with md5.
     */
    public T setPasswordApi(String password) {
        params.put("pass_api", password);
        return (T) this;
    }

    /**
     * Set credit limit granted to account.
     */
    public T setLimit(int limit) {
        params.put("limit", Integer.toString(limit));
        return (T) this;
    }

    /**
     * Set credit limit granted to account.
     */
    public T setLimit(double limit) {
        params.put("limit", Double.toString(limit));
        return (T) this;
    }

    /**
     * Set month credits, the amount that will be granted 1st day of every month.
     */
    public T setMonthLimit(int limit) {
        params.put("month_limit", Integer.toString(limit));
        return (T) this;
    }

    /**
     * Set month credits, the amount that will be granted 1st day of every month.
     */
    public T setMonthLimit(double limit) {
        params.put("month_limit", Double.toString(limit));
        return (T) this;
    }

    /**
     * Set access to main account sender names.
     */
    public T setFullAccessSenderNames(boolean access) {
        params.put("senders", access ? "1" : "0");
        return (T) this;
    }

    /**
     * Set access to main account phonebook contacts.
     */
    public T setFullAccessPhoneBook(boolean access) {
        params.put("phonebook", access ? "1" : "0");
        return (T) this;
    }

    /**
     * Set account active status.
     */
    public T setActive(boolean val) {
        params.put("active", val ? "1" : "0");
        return (T) this;
    }

    /**
     * Set additional account description.
     */
    public T setInfo(String info) {
        params.put("info", info);
        return (T) this;
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
