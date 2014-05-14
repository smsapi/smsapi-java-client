package pl.smsapi.api.action.phonebook;

public class PhonebookGroupEdit extends PhonebookGroupAdd {

	public PhonebookGroupEdit setGroup(String groupname) {
		params.put("edit_group", groupname);
		return this;
	}

	@Override
	public PhonebookGroupEdit setName(String groupname) {
		params.put("name", groupname);
		return this;
	}
}
