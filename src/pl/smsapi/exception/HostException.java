
package pl.smsapi.exception;


public class HostException extends SmsapiException{
	
	private int code;
	
	public HostException(String message){
		super(message);
	}
	
	public HostException(String message, int code){
		this(message);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
}
