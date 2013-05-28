package pl.smsapi.api.action.phonebook;

import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.response.ContactResponse;

@ActionResponse(object = "ContactResponse")
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
