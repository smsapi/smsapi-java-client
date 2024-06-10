package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

public class SendStatusResponse extends StatusResponse {

    private final int parts;

    public SendStatusResponse(int count, int parts, JSONArray jsonArray) {
        super(count, jsonArray);
        this.parts = parts;
    }

    @Override
    protected MessageResponse buildItem(JSONObject jsonObject) {
        MessageResponse response = super.buildItem(jsonObject);
        response.setToBeSentAtTimestamp(jsonObject.optInt("date_sent"));
        return response;
    }

    public int getParts() {
        return parts;
    }
}
