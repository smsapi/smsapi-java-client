package pl.smsapi.api.action.contacts.groups;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class Groups extends ListResponse<Group> {
    private Groups(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected Group buildItem(JSONObject jsonObject) {
        return new Group.GroupFromJsonFactory().createFrom(jsonObject);
    }

    public static class GroupsFromJsonFactory {
        public Groups createFrom(JSONObject jsonObject) {
            return new Groups(
                jsonObject.getInt("size"),
                jsonObject.getJSONArray("collection")
            );
        }

        public Groups createFrom(JSONArray jsonArray) {
            return new Groups(
                jsonArray.length(),
                jsonArray
            );
        }
    }
}
