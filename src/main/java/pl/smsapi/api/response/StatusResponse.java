package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatusResponse extends ListResponse<MessageResponse> {

    public StatusResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected MessageResponse buildItem(JSONObject jsonObject) {
        return new MessageResponse(
            jsonObject.optString("id"),
            jsonObject.optString("points"),
            jsonObject.optString("number"),
            jsonObject.optString("status"),
            jsonObject.optString("error"),
            jsonObject.optString("idx")
        );
    }
}
