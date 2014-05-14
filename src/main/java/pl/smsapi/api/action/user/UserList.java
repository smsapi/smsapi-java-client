package pl.smsapi.api.action.user;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.UsersResponse;

public class UserList extends AbstractAction<UsersResponse> {

    public UserList() {
        setJson(true);
        params.put("list", "1");
    }

    protected UsersResponse createResponse(String data) {
        return new UsersResponse(data);
    }

    @Override
    protected String endPoint() {
        return "user.do";
    }
}
