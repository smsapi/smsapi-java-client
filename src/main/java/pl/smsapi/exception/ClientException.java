package pl.smsapi.exception;

/**
 * Legacy API's client side errors related exception. Covers account/user related issues like not authorized access, no
 * funds for shipping, etc.
 *
 * @deprecated use @link pl.smsapi.exception.SmsapiLegacyErrorException() instead
 */
public class ClientException extends SmsapiLegacyErrorException {

    public ClientException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}