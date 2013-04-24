
package pl.smsapi.message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.SmsapiException;


public class Subuser {
	
	private String name;
	private String info;
	private String accessPhones;
	private Float limit;
	private Float limitMonth;
	private boolean senders;
	private boolean active;
	private Object error;
	
	public Subuser(String name)
	{
		this.name = name;
	}
	
	public Subuser(Map<String, String> data) {
		
		setName(data.get("username"));
		setLimit(data.get("limit"));
		setLimitMonth(data.get("month_limit"));
		setSenders(data.get("senders"));
		setAccessPhones(data.get("phonebook"));
		setActive(data.get("active"));
		setInfo(data.get("info"));
	}
	
	private Subuser setError(Object error) {
		this.error = error;
		return this;
	}
	
	public Object getError() {
		return error;
	}
	
	public boolean isError() {
		return (error != null);
	}

	public String getName() {
		return name;
	}
	
	public Subuser setName(String name) {
		this.name = name;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public Subuser setInfo(String info) {
		this.info = info;
		return this;
	}

	public String getLimit() {
		return limit.toString();
	}

	public Subuser setLimit(String limit) {
		this.limit = Float.parseFloat(limit);
		return this;
	}

	public String getLimitMonth() {
		return limitMonth.toString();
	}

	public Subuser setLimitMonth(String limitMonth) {
		this.limitMonth = Float.parseFloat(limitMonth);
		return this;
	}

	public boolean isSenders() {
		return senders;
	}

	public Subuser setSenders(boolean senders) {
		this.senders = senders;
		return this;
	}
	
	public Subuser setSenders(String senders) {
		this.senders =  senders.equals("1") ? true : false;
		return this;
	}

	public String getPhones() {
		return accessPhones;
	}

	public Subuser setAccessPhones(String access) {
		this.accessPhones = access;
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public Subuser setActive(boolean active) {
		this.active = active;
		return this;
	}
	
	public Subuser setActive(String active) {
		this.active = active.equals("1") ? true : false;
		return this;
	}
	
	
	public static Subuser[] parseSubusers(String str) throws SmsapiException
	{
	
		class Parser {
			
		  private int type;
		  private String str;
		  Subuser[] subusers;
			
			Parser(String str){
			 this.str = str;
			 String comp = str.substring(0,1);	 
		     type  = (comp.equals("[") == true) ? 1 : 2;
			 
			 if(str.contains("ERROR")){
				 type = 3;
			 }	 
			}
			
			public Subuser[] excecute()
			{		
			   try {	
				
				switch (type) {
					
					case 1:
						JSONArray aData = new JSONArray(str);
						final int n = aData.length();
						if (n > 0) {
							subusers = new Subuser[n];
							for (int i = 0; i < n; i++) {
								JSONObject tmp = aData.getJSONObject(i);
								subusers[i] = new Subuser(bildValues(tmp));
							}
						}else{
							subusers = null;
						}
					break;
						
					case 2:
						JSONObject oData = new JSONObject(str);
						subusers = new Subuser[1];
						subusers[0] = new Subuser(bildValues(oData));												
					 break;
						
					case 3:
						subusers = new Subuser[1];
						subusers[0] = new Subuser("").setError(SmsapiException.createError(str.split(":")));				
					  break;	
						
					default:
						subusers = null;
				}
				
			   } catch (JSONException ex) {
				   throw new SmsapiException("Problem with decode json format");
			   }	
				
				return subusers;
			}	
		}
		
	
		return new Parser(str).excecute();		
	}
	
	private static HashMap<String, String> bildValues(JSONObject val) {

		String key;
		Iterator it = val.keys();
		HashMap<String, String> data = new HashMap<String, String>();

		while (it.hasNext()) {
			key = (String) it.next();
			data.put(key, val.optString(key));
		}

		return data;
	}

}
