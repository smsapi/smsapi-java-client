package pl.smsapi.message;

import java.util.ArrayList;
import java.util.HashMap;
import pl.smsapi.sender.Sender;

public interface MessageInterface {

	public boolean send();

	public Object getObjMessage();
	
	public HashMap getParams();
	
	public String getUsername();
	
	public void setUsername(String username);
	
	public String getPassword();

	public void setPassword(String password);
	
	public void setPassword(String password, boolean encodeMD5);
	
	public Sender getSender();
	
	public void setSender(Sender sender);
	
	public ArrayList<Result> getResults();
	
	
}
