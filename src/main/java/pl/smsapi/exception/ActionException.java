
package pl.smsapi.exception;

/**
 * Legacy API's client side errors related exception. Covers action related issues like invalid input, no such resource,
 * etc.
 *
 * @deprecated use {@link pl.smsapi.exception.SmsapiLegacyErrorException()} instead
 */
public class ActionException extends SmsapiLegacyErrorException {

	public ActionException(String errorMessage, int errorCode) {
		super(errorMessage, errorCode);
	}
}
