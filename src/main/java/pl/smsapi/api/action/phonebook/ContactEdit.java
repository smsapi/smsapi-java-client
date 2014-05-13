package pl.smsapi.api.action.phonebook;

public class ContactEdit extends ContactAdd {

	public ContactEdit setContact(String number) {
		params.put("edit_contact", number);
		return this;
	}

	public ContactAdd setNumber(String number) {
		params.put("new_number", number);
		return this;
	}
}
