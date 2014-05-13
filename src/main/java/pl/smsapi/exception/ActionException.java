
package pl.smsapi.exception;


public class ActionException extends SmsapiException{
	
	private int code;
	
	public ActionException(String message){
		super(message);
	}
	
	public ActionException(String message, int code){
		this(message);
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
}
