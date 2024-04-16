package pl.smsapi.api.action.user;

import org.json.JSONArray;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.UsersResponse;

public class UserList extends AbstractAction<UsersResponse> {

    public UserList() {
        setJson(true);
        params.put("list", "1");
    }

    protected UsersResponse createResponse(String data) {
        JSONArray jsonArray = new JSONArray(data);
        return new UsersResponse(jsonArray.length(), jsonArray);
    }

    @Override
    protected String endPoint() {
        return "user.do";
    }
}
