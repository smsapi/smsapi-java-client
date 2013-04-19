
package pl.smsapi.message;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.SmsapiException;

public class Group {
	
	private Integer id;
	private String name;
	private String info;
	private Integer numbers = 0;
	private Object error;
	private boolean deleted;
	private Map<String, String> params;
	private Phonebook.Action action = null;
	
	public Group(String name){
		this(name, "");
	}
	
	public Group(String name, String info){
		this(name, info, null);
	}
	
	public Group(String name, String info, String numbers){
								
	    this.name = name;
		this.info = info;
		
		if(numbers != null && !numbers.isEmpty()){
			this.numbers = Integer.parseInt(numbers);
		}		
	}
	
	public Group(Phonebook.Action action, String name, Integer id, Map<String, String> params){
		this.params = params;
		this.name = name;
		this.id = id;
		this.action = action;
		initAction();
	}
	
	private void initAction(){
		this.params.put(this.action.getName(), this.name);
		
		if(id > 0){
			
		  switch(action)
		  {
			  case GROUP_EDIT:
				 params.put("group_id", id.toString());
			   break;

			  default:
				 params.put("id", id.toString());				  
		  }
			  		
		    params.put(action.getName(), "");
		}	
	}
		
	public void removeContacts(boolean delete){
		if(action.equals(Phonebook.Action.GROUP_DELETE.getName())){
			if(delete){
			   params.put("remove_contacts", "1");	
			}else{
			   params.remove("remove_contacts");
			}			 	
		}
	}
	
	
	public static Group[] parseGroups(String str) throws SmsapiException
	{
	
		class Parser {
			
		  private int type;
		  private String str;
		  Group[] groups;
			
			Parser(String str){
			 this.str = str;
			 String comp = str.substring(0,1);	 
		     type  = (comp.equals("[") == true) ? 1 : 2;		
			}
			
			public Group[] excecute()
			{
				
			   try {	
				
				switch (type) {
					
					case 1:
						JSONArray aData = new JSONArray(str);
						final int n = aData.length();
						if (n > 0) {
							groups = new Group[n];
							for (int i = 0; i < n; i++) {
								JSONObject tmp = aData.getJSONObject(i);
								groups[i] = new Group(tmp.optString("name"), tmp.optString("info"), tmp.optString("numbers_count"));
							}
						}else{
							groups = null;
						}
					break;
						
					case 2:
						JSONObject oData = new JSONObject(str);
						groups = new Group[1];
						groups[0] = parseGroup(oData);																
					 break;
						
					default:
						groups = null;
				}
				
			   } catch (JSONException ex) {
				   throw new SmsapiException("Problem with decode json format");
			   }	
				
				return groups;
			}	
		}
		
		return new Parser(str).excecute();		
	}

	private static Group parseGroup(JSONObject data) throws JSONException {

		if (!data.optString("error").isEmpty()) {
			return new Group("", "", "").setError(SmsapiException.createError(data.optString("error"), data.optString("message")));
		} else if (!data.optString("deleted").isEmpty()){
			return new Group("", "", data.optString("removed_contacts")).setDeletedFlag(true);
		} else {
			return new Group(data.optString("name"), data.optString("info"), data.optString("numbers_count"));
	    }   
   }
	
	private Group setError(Object error) {
		this.error = error;
		return this;
	}
	
	private Group setDeletedFlag(boolean val) {
		deleted = val;
		return this;
	}
	
	public boolean isDeleted(){
		return deleted;
	}
	
	public boolean isError() {
		return (error != null);
	}
    
	public Object getError() {
		return error;
	}
	
	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}

	public String getNumbers() {
				
		return  (numbers > 0) ? numbers.toString() : "";
	}
	
	public Group setName(String name) {
		
		if(params != null){
			params.put("name",name);
		}
		
		this.name = name;
		
		return this;
	}

	public Group setInfo(String info) {
		
		if(params != null){
			params.put("info",info);
		}
		
		this.info = info;
		
		return this;
	}
	
}
