package pl.smsapi.api.action.contacts.groups;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class Permissions extends ListResponse<Permission> {

    public Permissions(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected Permission buildItem(JSONObject jsonObject) {
        return new Permission.PermissionFromJsonFactory().createFrom(jsonObject);
    }

    public static class PermissionsFromJsonFactory {

        public Permissions createFrom(JSONArray jsonArray) {

            if (jsonArray == null) {
                return null;
            }

            return new Permissions(
                jsonArray.length(),
                jsonArray
            );
        }
    }
}
