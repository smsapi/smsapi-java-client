package pl.smsapi.api.action.phonebook;

public class PhonebookContactEdit extends PhonebookContactAdd {

	public PhonebookContactEdit setContact(String number) {
		params.put("edit_contact", number);
		return this;
	}

	public PhonebookContactAdd setNumber(String number) {
		params.put("new_number", number);
		return this;
	}
}
