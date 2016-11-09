package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.ContactResponse;

import java.util.Calendar;
import java.util.GregorianCalendar;

abstract public class AbstractContactControl<T> extends AbstractAction<ContactResponse> {

    /**
     * Set contact first name.
     */
    public T setFirstName(String firstName) {
        params.put("first_name", firstName);
        return (T) this;
    }

    /**
     * Set contact last name.
     */
    public T setLastName(String lastName) {
        params.put("last_name", lastName);
        return (T) this;
    }

    /**
     * Set additional contact description.
     */
    public T setInfo(String info) {
        params.put("info", info);
        return (T) this;
    }

    /**
     * Set contact email address.
     */
    public T setEmail(String email) {
        params.put("email", email);
        return (T) this;
    }

    /**
     * Set contact birthday date.
     *
     * @param birthday Date in DD.MM.YYYY format.
     */
    public T setBirthday(String birthday) {
        params.put("birthday", birthday);
        return (T) this;
    }

    /**
     * Set contact birthday date.
     */
    public T setBirthday(GregorianCalendar cal) {
        String tmp = Integer.toString(cal.get(Calendar.DAY_OF_MONTH))
                + "." + Integer.toString(cal.get(Calendar.MONTH))
                + "." + Integer.toString(cal.get(Calendar.YEAR));
        params.put("birthday", tmp);
        return (T) this;
    }

    /**
     * Set contact city.
     */
    public T setCity(String city) {
        params.put("city", city);
        return (T) this;
    }

    /**
     * Set contact gender.
     */
    public T setGender(ContactResponse.Gender gender) {
        params.put("gender", gender.type().toString());
        return (T) this;
    }

    /**
     * Set contact gender.
     */
    public T setGender(String gender) {

        if (gender.equalsIgnoreCase(ContactResponse.Gender.FEMALE.name())) {
            setGender(ContactResponse.Gender.FEMALE);
        } else if (gender.equalsIgnoreCase(ContactResponse.Gender.MALE.name())) {
            setGender(ContactResponse.Gender.MALE);
        }

        return (T) this;
    }

    /**
     * Add contact to group.
     */
    public T setGroup(String group) {
        params.put("groups", group);
        return (T) this;
    }

    /**
     * Add contact to groups.
     */
    public T setGroups(String[] groups) {
        params.put("groups", StringUtils.join(groups, ','));
        return (T) this;
    }

    protected ContactResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return
                new ContactResponse(
                        jsonObject.getString("number"),
                        jsonObject.optString("first_name"),
                        jsonObject.optString("last_name"),
                        jsonObject.optString("info"),
                        jsonObject.optString("birthday"),
                        jsonObject.optString("city"),
                        jsonObject.optString("gender"),
                        jsonObject.optInt("date_add"),
                        jsonObject.optInt("date_mod")
                );
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
