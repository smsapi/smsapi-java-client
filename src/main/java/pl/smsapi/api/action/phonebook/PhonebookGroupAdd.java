package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.GroupResponse;

public class PhonebookGroupAdd extends AbstractAction<GroupResponse> {

    /**
     * Set group name.
     */
    public PhonebookGroupAdd setName(String groupName) {
        params.put("add_group", groupName);
        return this;
    }

    /**
     * Set additional group description.
     */
    public PhonebookGroupAdd setInfo(String info) {
        params.put("info", info);
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
