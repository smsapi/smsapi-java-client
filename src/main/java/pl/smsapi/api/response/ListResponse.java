package pl.smsapi.api.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

abstract public class ListResponse<T> extends CountableResponse {

    public final ArrayList<T> list = new ArrayList<>();

    public ListResponse(int count, JSONArray jsonArray) {
        super(count);

        if (jsonArray != null) {
            final int n = jsonArray.length();
            for (int i = 0; i < n; i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                list.add(buildItem(row));
            }
        }
    }

    abstract protected T buildItem(JSONObject jsonObject);

    /**
     * @deprecated use {@link #list} instead
     */
    @Deprecated
    public ArrayList<T> getList() {
        return list;
    }
}
