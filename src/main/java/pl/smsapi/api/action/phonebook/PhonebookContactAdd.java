package pl.smsapi.api.action.phonebook;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONObject;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.ContactResponse;

public class PhonebookContactAdd extends BaseAction<ContactResponse> {

	private ArrayList<String> groups = new ArrayList<String>();

	@Override
	public URI uri() throws URISyntaxException {

		String query;

		query = paramsLoginToQuery();

		query += paramsOther();

		if (!groups.isEmpty()) {
			String tmp[] = new String[groups.size()];
			groups.toArray(tmp);
			query += "&groups=" + join(tmp, ",");
		}

		return new URI(proxy.getProtocol(), null, proxy.getHost(), proxy.getPort(), proxy.getPath()+"phonebook.do", query, null);
	}

	public PhonebookContactAdd setNumber(String number) {
		params.put("add_contact", number);
		return this;
	}

	public PhonebookContactAdd setFirstName(String firstName) {
		params.put("first_name", firstName);
		return this;
	}

	public PhonebookContactAdd setLastName(String lastName) {
		params.put("last_name", lastName);
		return this;
	}

	public PhonebookContactAdd setInfo(String info) {
		params.put("info", info);
		return this;
	}

	public PhonebookContactAdd setEmail(String email) {
		params.put("email", email);
		return this;
	}

	public PhonebookContactAdd setBirthday(String birthday) {
		params.put("birthday", birthday);
		return this;
	}

	public PhonebookContactAdd setBirthday(GregorianCalendar cal) {
		String tmp = Integer.toString(cal.get(Calendar.DAY_OF_MONTH))
				+ "." + Integer.toString(cal.get(Calendar.MONTH))
				+ "." + Integer.toString(cal.get(Calendar.YEAR));
		params.put("birthday", tmp);
		return this;
	}

	public PhonebookContactAdd setCity(String city) {
		params.put("city", city);
		return this;
	}

	public PhonebookContactAdd setGender(ContactResponse.Gender gender) {
		params.put("gender", gender.type().toString());
		return this;
	}

	public PhonebookContactAdd setGender(String gender) {

		if (gender.equalsIgnoreCase(ContactResponse.Gender.FEMALE.name())) {
			setGender(ContactResponse.Gender.FEMALE);
		} else if (gender.equalsIgnoreCase(ContactResponse.Gender.MALE.name())) {
			setGender(ContactResponse.Gender.MALE);
		}


		return this;
	}

	public PhonebookContactAdd setGroup(String group) {
		groups.add(group);
		return this;
	}

	public PhonebookContactAdd setGroups(String[] groups) {
		for (String item : groups) {
			setGroup(item);
		}
		return this;
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
}
