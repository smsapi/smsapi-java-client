package pl.smsapi;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;

public interface Client {
	AuthenticationStrategy getAuthenticationStrategy();
}
