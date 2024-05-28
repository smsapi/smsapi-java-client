
package pl.smsapi.exception;


public class ProxyException extends SmsapiException {
	
	private int code;
	
	public ProxyException(String message){
		super(message);
	}

    public ProxyException(String message, Throwable cause) {
        super(message, cause);
    }

	/**
	 * @deprecated no replacement
	 */
	public ProxyException(String message, int code){
		this(message);
		this.code = code;
	}

	/**
	 * @deprecated no replacement
	 */
	public int getCode(){
		return code;
	}
}
