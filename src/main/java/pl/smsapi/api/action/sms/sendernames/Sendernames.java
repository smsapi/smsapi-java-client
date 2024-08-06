package pl.smsapi.api.action.sms.sendernames;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class Sendernames extends ListResponse<Sendername> {
    private Sendernames(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected Sendername buildItem(JSONObject jsonObject) {
        return new Sendername.SendernameFactory().createFrom(jsonObject);
    }

    static class SendernamesFromJsonFactory {
        public Sendernames createFrom(JSONObject jsonObject) {
            return new Sendernames(
                jsonObject.getInt("size"),
                jsonObject.getJSONArray("collection")
            );
        }
    }
}
