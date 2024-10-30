package pl.smsapi.exception;

public class SmsapiException extends Exception {

	public SmsapiException(String message) {
		super(message);
	}

	public SmsapiException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @deprecated use @link SmsapiLegacyErrorException#isClientError(int) instead
	 */
	public static boolean isClientError(int code) {
		return SmsapiLegacyErrorException.isClientError(code);
	}

	/**
	 * @deprecated use @link SmsapiLegacyErrorException#isHostError(int) instead
	 */
	public static boolean isHostError(int code) {
		return SmsapiLegacyErrorException.isHostError(code);
	}
}
