package pl.smsapi.exception;

/**
 * API's client and server side errors related exception.
 */
public class SmsapiErrorException extends SmsapiException {

    private final String error;

    public SmsapiErrorException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
