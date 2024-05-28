package pl.smsapi.exception;

/**
 * Legacy API's client and server side errors related exception.
 */
abstract public class SmsapiLegacyErrorException extends SmsapiException {

    protected int code;

    public SmsapiLegacyErrorException(String message, int code) {
        super(message);
        this.code = code;
    }

    public static boolean isClientError(int code) {
        if (code == 101) {
            return true;
        }

        if (code == 102) {
            return true;
        }

        if (code == 103) {
            return true;
        }

        if (code == 105) {
            return true;
        }

        if (code == 110) {
            return true;
        }

        if (code == 1000) {
            return true;
        }

        if (code == 1001) {
            return true;
        }

        return false;
    }

    public static boolean isHostError(int code) {

        if (code == 8) {
            return true;
        }

        if (code == 201) {
            return true;
        }

        if (code == 666) {
            return true;
        }

        if (code == 999) {
            return true;
        }


        return false;
    }

    public int getCode() {
        return code;
    }
}
