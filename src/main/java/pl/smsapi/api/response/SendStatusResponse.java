package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendStatusResponse extends StatusResponse {

    private int parts;

    public SendStatusResponse(int count, int parts, JSONArray list) {
        super(count, list);
        this.parts = parts;
    }

    public int getParts() {
        return parts;
    }
}
