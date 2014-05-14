package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CheckNumberResponse extends CountableResponse {

    private ArrayList<NumberResponse> list = new ArrayList<NumberResponse>();

    public CheckNumberResponse(int count, JSONArray array) {

        super(count);

        if (array != null) {

            int arrayLength = array.length();
            for (int i = 0; i < arrayLength; i++) {

                JSONObject tmp = array.getJSONObject(i);
                list.add(
                        new NumberResponse(
                                tmp.optString("id"),
                                tmp.optString("number"),
                                tmp.optInt("mcc"),
                                tmp.optInt("mnc"),
                                tmp.optString("info"),
                                tmp.optString("status"),
                                tmp.optInt("date"),
                                tmp.optInt("ported"),
                                tmp.optInt("ported_from"),
                                tmp.optString("price")
                        )
                );
            }
        }
    }

    public ArrayList<NumberResponse> getList() {
        return list;
    }
}
