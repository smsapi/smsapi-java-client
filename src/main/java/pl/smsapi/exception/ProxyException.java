
package pl.smsapi.exception;


public class ProxyException extends SmsapiException{
	
	private int code;
	
	public ProxyException(String message){
		super(message);
	}
	
	public ProxyException(String message, int code){
		this(message);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
		
}
