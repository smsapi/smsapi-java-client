
package pl.smsapi.message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.SmsapiException;


public class Sname {
	
	public static enum Status {ACTIVE, PENDING};
	
	private String sender;
	private Status status;
	private boolean main;
	private Object error;
	
	public Sname(String sender, String status, String main){
		this.sender = sender;
		setStatus(status);
		setMain(main);		
	}

	public String getSender() {
		return sender;
	}
	
	public String getStatus() {
		return status.name();
	}

	public boolean isMain() {
		return main;
	}
	
	public Sname setStatus(String status){
		if(status.equalsIgnoreCase(Status.ACTIVE.name())){
			this.status = Status.ACTIVE;
		}else if (status.equalsIgnoreCase(Status.PENDING.name())){
			this.status = Status.PENDING;
		}
		return this;
	}
	
	public Sname setMain(String status){
		if(status.equalsIgnoreCase("false")){
			this.main = false;
		}else if (status.equalsIgnoreCase("true")){
			this.main = true;
		}
		return this;
	}
	
	private Sname setError(Object error) {
		this.error = error;
		return this;
	}
	
	public Object getError() {
		return error;
	}
	
	public boolean isError() {
		return (error != null);
	}
	
	public static Sname[] parsSnames(String str) throws SmsapiException
	{
	
		class Parser {
			
		  private int type;
		  private String str;
		  Sname[] names;
			
			Parser(String str){
			 this.str = str;
			 String comp = str.substring(0,1);	 
		     type  = (comp.equals("[") == true) ? 1 : 2;
			 
			 if(str.contains("ERROR")){
				 type = 3;
			 }	 
			}
			
			public Sname[] excecute()
			{		
			   try {	
				
				switch (type) {
					
					case 1:
						JSONArray aData = new JSONArray(str);
						final int n = aData.length();
						if (n > 0) {
							names = new Sname[n];
							for (int i = 0; i < n; i++) {
								JSONObject tmp = aData.getJSONObject(i);
								names[i] = new Sname(tmp.optString("sender"), tmp.optString("status"), tmp.optString("default"));
							}
						}else{
							names = null;
						}
					break;
						
					case 2:
						JSONObject oData = new JSONObject(str);
						names = new Sname[1];
						names[0] = new Sname(oData.optString("sender"), oData.optString("status"), oData.optString("default"));	
					 break;
						
					case 3:
						names = new Sname[1];
						names[0] = new Sname("","","").setError(SmsapiException.createError(str.split(":")));				
					  break;	
						
					default:
						names = null;
				}
				
			   } catch (JSONException ex) {
				   throw new SmsapiException("Problem with decode json format");
			   }	
				
				return names;
			}	
		}
			
		return new Parser(str).excecute();		
	}
		
}
