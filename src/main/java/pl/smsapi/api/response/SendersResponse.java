package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SendersResponse implements Response {

    private ArrayList<SenderResponse> list = new ArrayList<SenderResponse>();

    public SendersResponse(String data) {

        if (data != null && !data.isEmpty()) {

            JSONArray aData = new JSONArray(data);
            final int n = aData.length();
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    JSONObject tmp = aData.getJSONObject(i);
                    list.add(new SenderResponse(tmp.optString("sender"), tmp.optString("status"), tmp.optBoolean("default")));
                }
            }
        }
    }

    public ArrayList<SenderResponse> getList() {
        return list;
    }
}
