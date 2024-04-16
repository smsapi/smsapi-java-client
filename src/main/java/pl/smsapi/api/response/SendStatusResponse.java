package pl.smsapi.api.response;

import org.json.JSONArray;

public class SendStatusResponse extends StatusResponse {

    private final int parts;

    public SendStatusResponse(int count, int parts, JSONArray jsonArray) {
        super(count, jsonArray);
        this.parts = parts;
    }

    public int getParts() {
        return parts;
    }
}
