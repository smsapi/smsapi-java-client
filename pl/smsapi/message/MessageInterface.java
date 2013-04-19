package pl.smsapi.message;

import java.util.List;
import java.util.Map;
import pl.smsapi.sender.Sender;

public interface MessageInterface {

	public boolean send();
	
	public String getPath();
	
	public Object getObjMessage();
	
	public Map getParams();
	
	public String getUsername();
	
	public void setUsername(String username);
	
	public String getPassword();

	public void setPassword(String password);
	
	public void setPassword(String password, boolean encodeMD5);
	
	public Sender getSender();
	
	public void setSender(Sender sender);
	
	public List<Result> getResults();
	
	
}
