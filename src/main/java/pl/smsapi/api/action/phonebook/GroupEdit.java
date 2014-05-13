package pl.smsapi.api.action.phonebook;

public class GroupEdit extends GroupAdd {

	public GroupEdit setGroup(String groupname) {
		params.put("edit_group", groupname);
		return this;
	}

	@Override
	public GroupEdit setName(String groupname) {
		params.put("name", groupname);
		return this;
	}
}
