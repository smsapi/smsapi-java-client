package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.GroupResponse;

public class PhonebookGroupEdit extends AbstractAction<GroupResponse> {

    /**
     * Set edited group name.
     */
    public PhonebookGroupEdit name(String groupname) {
        params.put("edit_group", groupname);
        return this;
    }

    /**
     * Set new group name.
     */
    public PhonebookGroupEdit setName(String groupname) {
        params.put("name", groupname);
        return this;
    }

    /**
     * Set additional group description.
     */
    public PhonebookGroupEdit setInfo(String info) {
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
