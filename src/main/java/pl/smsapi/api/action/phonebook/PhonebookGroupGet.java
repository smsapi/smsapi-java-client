package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.GroupResponse;

public class PhonebookGroupGet extends AbstractAction<GroupResponse> {

    public PhonebookGroupGet() {
        setJson(true);
    }

    /**
     * Set group name to find.
     */
    public PhonebookGroupGet name(String groupName) {
        params.put("get_group", groupName);
        return this;
    }

    protected GroupResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new GroupResponse(jsonObject.getString("name"), jsonObject.optString("info"), jsonObject.optInt("numbers_count"));
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
