package pl.smsapi;

import pl.smsapi.api.authenticationStrategy.BasicAuthenticationStrategy;
import pl.smsapi.exception.ClientException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @deprecated use {@link OAuthClient} instead
 */
@Deprecated
public class BasicAuthClient implements Client {
    private final String username;
    private final String password;

    public BasicAuthClient(String username, String password) {
        assert username != null && !username.isEmpty() : "Username is empty";
        assert password != null && !password.isEmpty() : "Password is empty";
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public static String MD5Digest(String str) throws ClientException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new ClientException("MD5 algorithm not found. Cannot create API password.", -1);
        }
    }

    public static BasicAuthClient createFromRawPassword(String username, String password) throws ClientException {
        return new BasicAuthClient(username, MD5Digest(password));
    }

    @Override
    public BasicAuthenticationStrategy getAuthenticationStrategy() {
        return new BasicAuthenticationStrategy(username, password);
    }
}
