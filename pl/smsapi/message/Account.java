
package pl.smsapi.message;

import java.util.ArrayList;
import java.util.HashMap;
import pl.smsapi.SmsapiException;
import pl.smsapi.sender.Sender;
import pl.smsapi.sender.SenderHttp;


public final class Account implements MessageInterface {
	
	private String username;
	private String password;
	private final HashMap<String, String> params = new HashMap<String, String>();
	private Sender sender;
	private String path = "user.do";
	
	private static HashMap<String, String> credits = new HashMap<String, String>();
	
	public Account() {}

	
	public Account(final Sender sender) {
		this.sender = sender;
	}

	public HashMap<String, String> getCredits() {
		return Account.credits;
	}
	
	public static void parseCredits(String str)
	{
		String[] tmp = str.split(";");
		
		if(tmp.length >= 6){
			String[] tmpPoint = tmp[0].split(":");
			
			if(tmpPoint.length == 2){
				credits.put("POINTS", tmpPoint[1].trim());
			}
			credits.put("PRO", tmp[1]);
			credits.put("ECO", tmp[2]);
			credits.put("MMS", tmp[3]);
			credits.put("VMS_GMS", tmp[4]);
			credits.put("VMS_STC", tmp[5].trim());
		}
	}
	
	@Override
	public String getPath() {
		return path;
	}

	protected void setPath(final String path) {
		this.path = path;
	}
	
	@Override
	public boolean send() {

		if (sender == null) {
			throw new SmsapiException("No exists sender");
		}

		sender.setMessage(this);

		return sender.send();
	}
	
	public boolean send(SenderHttp.RequestMethod requestMethod) {
		
		if (sender == null) {
			throw new SmsapiException("No exists sender");
		}

		sender.setMethod(requestMethod);

		boolean result = this.send();

		return result;
	}
	
	@Override
	public final Sender getSender() {
		return sender;
	}

	@Override
	public final void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public Object getObjMessage() {
		return null;
	}
	
	@Override
	public ArrayList<Result> getResults() {

		if (sender == null) {
			throw new SmsapiException("No exists sender");
		}

		return sender.getResults();
	}
	
	public HashMap getParams() {
		return params;
	}

	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setPassword(String password, boolean encodeMD5) {

		this.password = (encodeMD5 == true) ? Message.MD5Digest(password) : password;
	}
	
	
	public void setCredits(boolean credits) {
		if (credits == true) {
			params.put("credits", "1");
		} else {
			params.remove("credits");
		}
	}
	
	public void setCredits(boolean credits, boolean details) {
		setCredits(credits);
		setDetails(details);
	}
	
	private void setDetails(boolean details) {
		if (details == true) {
			params.put("details", "1");
		} else {
			params.remove("details");
		}
	}
	
	public Sendername getSendername()
	{
		setPath("sender.do");
		params.clear();
		return new Sendername();
	}
	
	
	public Subuser getSubuser()
	{
		setPath("user.do");
		params.clear();
		return new Subuser();		
	}
	
	public class Subuser {
		
	  protected Subuser(){}
	  
	  public final static String ADD = "add_user";
	  public final static String EDIT = "set_user"; 
	  
	  public class AcountSubuser {
		 	  
		protected AcountSubuser(String action, String name){
			
			if(action.equals(Subuser.ADD)){
				params.put(Subuser.ADD, name);
			}else if(action.equals(Subuser.EDIT)){
				params.put(Subuser.EDIT, name);
			}					
		}
		
		protected void setPass(String password){
			params.put("pass", password);
		}
		
		public AcountSubuser setPassApi(String password){
			params.put("pass_api", password);
			return this;
		}
		
		public AcountSubuser setPassApi(String password, boolean encodeMd5){		
		  if(encodeMd5 == true){
			 params.put("pass_api", Message.MD5Digest(password));
		  }else{
			 params.put("pass_api", password);  
		  }
		   return this;
		}
		
		public AcountSubuser setLimit(String limit){
			params.put("limit", limit);
			return this;
		}
		
		public AcountSubuser setMonthLimit(String limit){
			params.put("limit", limit);
			return this;
		}
		
		public AcountSubuser setSenders(boolean access){
			if(access == true){
				params.put("senders", "1");
			}else{
				params.put("senders", "0");
			}
			return this;
		}
		
		public AcountSubuser setPhonebook(boolean access){
			if(access == true){
				params.put("phonebook", "1");
			}else{
				params.put("phonebook", "0");
			}
			return this;
		}
		
		public AcountSubuser setActive(boolean val){
			if(val == true){
				params.put("active", "1");
			}else{
				params.put("active", "0");
			}
			return this;
		}
		
		public AcountSubuser setInfo(String info){
			params.put("info", info);
			return this;
		}
		
	  };
		
	  public HashMap<String, String> getParams()
	  {
		  return params;
	  }
	  
	  
	  public AcountSubuser addUser(String username, String password)
	  {
	      params.clear();
		  AcountSubuser user = new AcountSubuser(Subuser.ADD, username);
		  user.setPass(password);
		  return user;	   	  	  
	  }
	  
	  public AcountSubuser addUser(String username, String password, boolean encodeMd5)
	  {
	      params.clear();
		  AcountSubuser user = new AcountSubuser(Subuser.ADD, username);
		  if(encodeMd5 == true){
			 user.setPass(Message.MD5Digest(password)); 
		  }else{
			 user.setPass(password); 
		  }
		  
		  return user;	   	  	  
	  }
	  
	  
	  public AcountSubuser editUser(String username)
	  {
		  params.clear();
		  AcountSubuser user = new AcountSubuser(Subuser.EDIT, username);	  
		  return user;		  	  
	  }
	  
	  
	  public void setUser(String username){
		  params.put("get_user", username);
	  }
	  
	   public void setUser(String username, boolean json){
		  params.put("get_user", username);
		  if(json == true){
				setFormatJson();
		  }else{
				removeFormatJson();
		  }
	  }
	  
	  public void setList(boolean list){
		 if (list == true) {
			params.put("list", "1");			
		} else {
			params.remove("list");
		}
	  }
	    
	  public void setList(boolean list, boolean json){
		 if (list == true) {
			params.put("list", "1");
			if(json == true){
				setFormatJson();
			}else{
				removeFormatJson();
			}					
		} else {
			params.remove("list");
		}
	  }
	  
	  private void setFormatJson()
	  {
		  params.put("format", "json");
	  }
	  
	  private void removeFormatJson()
	  {
		  params.remove("format");
	  }
		
	}
	
	
    public class Sendername {
			
	  protected Sendername(){}			
	  
	  public HashMap<String, String> getParams()
	  {
		  return params;
	  }
	 	
	  public void setDefault(String name){
		params.put("default", name);
	  }
	  
	  public void setAdd(String name)
	  {
		  params.put("add", name);
	  }
	  
	  public void setDelete(String name)
	  {
		  params.put("delete", name);
	  }
	  
	  public void setStatus(String name){
		params.put("status", name);
	  }
	  
	  public void setStatus(String name, boolean json){
		params.put("status", name);
		if(json == true){
				setFormatJson();
		}else{
				removeFormatJson();
		}
	  }
	  
	  public void setList(boolean list){
		 if (list == true) {
			params.put("list", "1");			
		} else {
			params.remove("list");
		}
	  }
	    
	  public void setList(boolean list, boolean json){
		 if (list == true) {
			params.put("list", "1");
			if(json == true){
				setFormatJson();
			}else{
				removeFormatJson();
			}					
		} else {
			params.remove("list");
		}
	  }
	  
	  private void setFormatJson()
	  {
		  params.put("format", "json");
	  }
	  
	  private void removeFormatJson()
	  {
		  params.remove("format");
	  }
	  
	}
	
}
