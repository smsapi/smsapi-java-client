package pl.smsapi.api.action.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.action.contacts.groups.Groups;
import pl.smsapi.api.response.Response;

public class Contact implements Response {

    public final String id;
    public final String firstName;
    public final String lastName;
    public final String birthdayDate;
    public final String phoneNumber;
    public final String email;
    public final String gender;
    public final String city;
    public final String country;
    public final String dateCreated;
    public final String dateUpdated;
    public final String description;
    public final Groups groups;
    public final String source;

    private Contact(String id, String firstName, String lastName, String birthdayDate, String phoneNumber, String email, String gender, String city, String country, String dateCreated, String dateUpdated, String description, JSONArray groups, String source) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.description = description;
        this.groups = new Groups.GroupsFromJsonFactory().createFrom(groups);
        this.source = source;
    }

    public static class ContactFromJsonFactory {
        public Contact createFrom(JSONObject jsonObject) {
            return new Contact(
                jsonObject.getString("id"),
                jsonObject.optString("first_name", null),
                jsonObject.optString("last_name", null),
                jsonObject.optString("birthday_date", null),
                jsonObject.optString("phone_number", null),
                jsonObject.optString("email", null),
                jsonObject.getString("gender"),
                jsonObject.optString("city", null),
                jsonObject.optString("country", null),
                jsonObject.getString("date_created"),
                jsonObject.getString("date_updated"),
                jsonObject.optString("description", null),
                jsonObject.getJSONArray("groups"),
                jsonObject.optString("source", null)
            );
        }
    }
}
