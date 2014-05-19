package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.GroupsResponse;

public class PhonebookGroupList extends AbstractAction<GroupsResponse> {

    public PhonebookGroupList() {
        setJson(true);
        params.put("list_groups", "1");
    }

    protected GroupsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new GroupsResponse(jsonObject.optInt("count"), jsonObject.optString("list"));
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
