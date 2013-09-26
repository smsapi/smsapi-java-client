package pl.smsapi.api.action.user;

import pl.smsapi.api.action.ActionResponse;

public class Edit extends Add {

	@Override
	public Edit setUsername(String username) {
		params.put("set_user", username);
		return this;
	}
}
