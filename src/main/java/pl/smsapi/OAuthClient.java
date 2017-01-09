package pl.smsapi;

import pl.smsapi.api.authenticationStrategy.AuthenticationStrategy;
import pl.smsapi.api.authenticationStrategy.BearerAuthenticationStrategy;
import pl.smsapi.exception.ClientException;

public class OAuthClient implements Client {
	private String token;

	private OAuthClient() {}

	public OAuthClient(String token) throws ClientException {
		setToken(token);
	}

	public void setToken(String token) throws ClientException {
		if (token == null || token.isEmpty()) {
			throw new ClientException("Token can not be empty");
		}

		this.token = token;
	}

	public String getToken() {
		return token;
	}

	@Override
	public AuthenticationStrategy getAuthenticationStrategy() {
		return new BearerAuthenticationStrategy(this.token);
	}
}
