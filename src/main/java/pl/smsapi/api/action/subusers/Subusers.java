package pl.smsapi.api.action.subusers;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class Subusers extends ListResponse<Subuser> {
    private Subusers(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected Subuser buildItem(JSONObject jsonObject) {
        return new Subuser.SubuserFromJsonFactory().createFrom(jsonObject);
    }

    public static class SubusersFromJsonFactory {
        public Subusers createFrom(JSONObject jsonObject) {
            return new Subusers(
                jsonObject.getInt("size"),
                jsonObject.getJSONArray("collection")
            );
        }
    }
}
