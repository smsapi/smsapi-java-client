package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersResponse extends ListResponse<UserResponse> {

    public UsersResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected UserResponse buildItem(JSONObject jsonObject) {
        return new UserResponse(
            jsonObject.optString("username"),
            jsonObject.optDouble("limit"),
            jsonObject.optDouble("month_limit"),
            jsonObject.optInt("senders"),
            jsonObject.optInt("phonebook"),
            jsonObject.optInt("active"),
            jsonObject.optString("info")
        );
    }
}
