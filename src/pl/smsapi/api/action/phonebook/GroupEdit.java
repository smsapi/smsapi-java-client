package pl.smsapi.api.action.phonebook;

import pl.smsapi.api.action.ActionResponse;
import pl.smsapi.api.action.BaseAction;

@ActionResponse(object = "GroupResponse")
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
