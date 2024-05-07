package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.contacts.ContactsGroupResponse;

/**
 * @deprecated do not extend, use concrete action
 */
@Deprecated
abstract public class AbstractContactsGroupControl<T> extends AbstractAction<ContactsGroupResponse> {

    public T setName(String name) {
        params.put("name", name);
        return (T) this;
    }

    public T setDescription(String description) {
        params.put("description", description);
        return (T) this;
    }

    public T setIdx(String idx) {
        params.put("idx", idx);
        return (T) this;
    }

    protected ContactsGroupResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);

        return new ContactsGroupResponse(
                jsonObject.optString("id"),
                jsonObject.optString("name"),
                jsonObject.optString("description"),
                jsonObject.optString("date_created"),
                jsonObject.optString("date_updated"),
                jsonObject.optString("created_by"),
                jsonObject.optString("idx"),
                jsonObject.optJSONArray("permissions")
        );
    }

    @Override
    protected String endPoint() {
        return "groups";
    }
}
