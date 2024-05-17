package pl.smsapi.api.action.contacts;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.smsapi.api.action.contacts.groups.Groups;
import pl.smsapi.api.response.Response;

public class Contact implements Response {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String birthdayDate;
    private final String phoneNumber;
    private final String email;
    private final String gender;
    private final String city;
    private final String country;
    private final String dateCreated;
    private final String dateUpdated;
    private final String description;
    private final Groups groups;
    private final String source;

    public Contact(String id, String firstName, String lastName, String birthdayDate, String phoneNumber, String email, String gender, String city, String country, String dateCreated, String dateUpdated, String description, JSONArray groups, String source) {
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

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public String getDescription() {
        return description;
    }

    public Groups getGroups() {
        return groups;
    }

    public String getSource() {
        return source;
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
