package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

public class CheckNumberResponse extends ListResponse<NumberResponse> {

    public CheckNumberResponse(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected NumberResponse buildItem(JSONObject jsonObject) {
        return new NumberResponse(
            jsonObject.optString("id"),
            jsonObject.optString("number"),
            jsonObject.optInt("mcc"),
            jsonObject.optInt("mnc"),
            jsonObject.optString("info"),
            jsonObject.optString("status"),
            jsonObject.optInt("date"),
            jsonObject.optInt("ported"),
            jsonObject.optInt("ported_from"),
            jsonObject.optString("price")
        );
    }
}
