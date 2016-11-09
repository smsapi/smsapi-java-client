package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.contacts.ContactsContactResponse;

import java.util.Calendar;
import java.util.GregorianCalendar;

abstract public class AbstractContactsContactControl<T> extends AbstractAction<ContactsContactResponse> {

    public T setPhoneNumber(String phoneNumber) {
        params.put("phone_number", phoneNumber);
        return (T) this;
    }

    public T setEmail(String email) {
        params.put("email", email);
        return (T) this;
    }

    public T setFirstName(String firstName) {
        params.put("first_name", firstName);
        return (T) this;
    }

    public T setLastName(String lastName) {
        params.put("last_name", lastName);
        return (T) this;
    }

    public T setGender(ContactsContactResponse.Gender gender) {
        params.put("gender", gender.type().toString());
        return (T) this;
    }

    public T setGender(String gender) {

        if (gender.equalsIgnoreCase(ContactsContactResponse.Gender.FEMALE.name())) {
            setGender(ContactsContactResponse.Gender.FEMALE);
        } else if (gender.equalsIgnoreCase(ContactsContactResponse.Gender.MALE.name())) {
            setGender(ContactsContactResponse.Gender.MALE);
        }

        return (T) this;
    }

    /**
     * @param birthdayDate Date in YYYY-MM-DD format.
     */
    public T setBirthdayDate(String birthdayDate) {
        params.put("birthday_date", birthdayDate);
        return (T) this;
    }

    public T setBirthdayDate(GregorianCalendar cal) {
        String tmp = Integer.toString(cal.get(Calendar.YEAR))
                + "-" + Integer.toString(cal.get(Calendar.MONTH))
                + "-" + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));

        setBirthdayDate(tmp);

        return (T) this;
    }

    public T setDescription(String description) {
        params.put("description", description);
        return (T) this;
    }

    public T setCity(String city) {
        params.put("city", city);
        return (T) this;
    }

    public T setSource(String source) {
        params.put("source", source);
        return (T) this;
    }

    protected ContactsContactResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);

        return new ContactsContactResponse(
                jsonObject.optString("id"),
                jsonObject.optString("first_name"),
                jsonObject.optString("last_name"),
                jsonObject.optString("birthday_date"),
                jsonObject.optString("phone_number"),
                jsonObject.optString("email"),
                jsonObject.optString("gender"),
                jsonObject.optString("city"),
                jsonObject.optString("country"),
                jsonObject.optString("date_created"),
                jsonObject.optString("date_updated"),
                jsonObject.optString("description"),
                jsonObject.optJSONArray("groups"),
                jsonObject.optString("source")
        );
    }

    @Override
    protected String endPoint() {
        return "";
    }
}
