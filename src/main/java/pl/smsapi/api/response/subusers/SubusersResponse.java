package pl.smsapi.api.response.subusers;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class SubusersResponse extends ListResponse<SubuserResponse> {
    public SubusersResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected SubuserResponse buildItem(JSONObject jsonObject) {
        return new SubuserResponse.SubuserFromJsonFactory().createFrom(jsonObject);
    }

    public static class SubusersFromJsonFactory {
        public SubusersResponse createFrom(JSONObject jsonObject) {
            return new SubusersResponse(
                jsonObject.getInt("size"),
                jsonObject.getJSONArray("collection")
            );
        }
    }
}
