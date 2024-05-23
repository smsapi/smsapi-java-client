package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class ContactAdd extends AbstractAction<Contact> {
    @Override
    protected String endPoint() {
        return "contacts";
    }

    public ContactAdd withPhoneNumber(String phoneNumber) {
        params.put("phone_number", phoneNumber);
        return this;
    }

    public ContactAdd withEmail(String email) {
        params.put("email", email);
        return this;
    }

    public ContactAdd withFirstName(String firstName) {
        params.put("first_name", firstName);
        return this;
    }

    public ContactAdd withLastName(String lastName) {
        params.put("last_name", lastName);
        return this;
    }

    public ContactAdd withGender(String gender) {
        params.put("gender", gender);
        return this;
    }

    public ContactAdd withBirthdayDate(String birthdayDate) {
        params.put("birthday_date", birthdayDate);
        return this;
    }

    public ContactAdd withDescription(String description) {
        params.put("description", description);
        return this;
    }

    public ContactAdd withCity(String city) {
        params.put("city", city);
        return this;
    }

    public ContactAdd withSource(String source) {
        params.put("source", source);
        return this;
    }

    @Override
    protected Contact createResponse(String data) {
        return new Contact.ContactFromJsonFactory().createFrom(new JSONObject(data));
    }
}
