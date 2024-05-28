package pl.smsapi.api.action.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.response.ListResponse;

public class Contacts extends ListResponse<Contact> {

    private Contacts(int count, JSONArray jsonArray) {
        super(count, jsonArray);
    }

    @Override
    protected Contact buildItem(JSONObject jsonObject) {
        return new Contact.ContactFromJsonFactory().createFrom(jsonObject);
    }

    static class ContactsFromJsonFactory {
        public Contacts createFrom(JSONObject jsonObject) {
            return new Contacts(
                jsonObject.getInt("size"),
                jsonObject.getJSONArray("collection")
            );
        }
    }
}
