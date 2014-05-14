package pl.smsapi.exception;

public class SmsapiException extends Exception {

	public SmsapiException(String message) {
		super(message);
	}

    public SmsapiException(String message, Throwable cause) {
        super(message, cause);
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
}
