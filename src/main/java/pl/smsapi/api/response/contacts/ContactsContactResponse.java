package pl.smsapi.api.response.contacts;

import org.json.JSONArray;
import pl.smsapi.api.response.Response;

/**
 * @deprecated use {@link pl.smsapi.api.action.contacts.Contact()} instead
 */
@Deprecated
public class ContactsContactResponse implements Response {
    private String id;
    private String firstName;
    private String lastName;
    private String birthdayDate;
    private String phoneNumber;
    private String email;
    private String gender;
    private String city;
    private String country;
    private String dateCreated;
    private String dateUpdated;
    private String description;
    private ContactsGroupListResponse groups;
    private String source;

    public ContactsContactResponse(String id, String firstName, String lastName, String birthdayDate, String phoneNumber, String email, String gender, String city, String country, String dateCreated, String dateUpdated, String description, JSONArray groups, String source) {
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
        this.groups = parseGroups(groups);
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

    public ContactsGroupListResponse getGroups() {
        return groups;
    }

    public String getSource() {
        return source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroups(ContactsGroupListResponse groups) {
        this.groups = groups;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private ContactsGroupListResponse parseGroups(JSONArray groups) {
        if (groups == null) {
            return new ContactsGroupListResponse(0, new JSONArray());
        }

        return new ContactsGroupListResponse(groups.length(), groups);
    }

    public static enum Gender {
        MALE("male"),
        FEMALE("female");
        private final String type;

        Gender(String type) {
            this.type = type;
        }

        public String type() {
            return type;
        }
    }
}
