package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class ContactEdit extends AbstractAction<Contact> {

    private final String id;

    public ContactEdit(String id) {
        this.id = id;
    }
    @Override
    protected String endPoint() {
        return "contacts/" + id;
    }

    @Override
    protected String httpMethod() {
        return "PUT";
    }

    public ContactEdit withPhoneNumber(String phoneNumber) {
        params.put("phone_number", phoneNumber);
        return this;
    }

    public ContactEdit withEmail(String email) {
        params.put("email", email);
        return this;
    }

    public ContactEdit withFirstName(String firstName) {
        params.put("first_name", firstName);
        return this;
    }

    public ContactEdit withLastName(String lastName) {
        params.put("last_name", lastName);
        return this;
    }

    public ContactEdit withGender(String gender) {
        params.put("gender", gender);
        return this;
    }

    public ContactEdit withBirthdayDate(String birthdayDate) {
        params.put("birthday_date", birthdayDate);
        return this;
    }

    public ContactEdit withDescription(String description) {
        params.put("description", description);
        return this;
    }

    public ContactEdit withCity(String city) {
        params.put("city", city);
        return this;
    }

    public ContactEdit withSource(String source) {
        params.put("source", source);
        return this;
    }

    @Override
    protected Contact createResponse(String data) {
        return new Contact.ContactFromJsonFactory().createFrom(new JSONObject(data));
    }
}
