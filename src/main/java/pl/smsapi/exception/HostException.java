package pl.smsapi.exception;

/**
 * Legacy API's server side errors related exception.
 *
 * @deprecated use @link pl.smsapi.exception.SmsapiLegacyErrorException() instead
 */
public class HostException extends SmsapiLegacyErrorException {

    public HostException(String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
