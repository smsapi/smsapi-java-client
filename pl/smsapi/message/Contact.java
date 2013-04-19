package pl.smsapi.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.smsapi.SmsapiException;

public class Contact {

	private Integer id;
	private String number;
	private String firstName;
	private String lastName;
	private String info;
	private String email;
	private String birthday;
	private String city;
	private String gender;
	private String dateAdd;
	private String dateMod;
	private ArrayList<String> groups = new ArrayList<String>();
	private Object error;
	private boolean deleted;
	private Map<String, String> params;
	private Phonebook.Action action = null;

	public static enum OrderBy {

		FIRST_NAME, LAST_NAME
	};

	public static enum OrderDir {

		ASC, DESC
	}

	public static enum Gender {

		MALE(-1),
		FEMALE(1);
		private final int type;

		Gender(int type) {
			this.type = type;
		}

		public Integer type() {
			return type;
		}
	};

	public Contact(String number) {
		this.number = number;
	}

	public Contact(Map<String, String> data) {
		number = data.get("number");
		firstName = data.get("first_name");
		lastName = data.get("last_name");
		info = data.get("info");
		email = data.get("email");
		birthday = data.get("birthday");
		city = data.get("city");
		gender = data.get("gender");
		dateAdd = data.get("dateAdd");
		dateMod = data.get("dateMod");
	}

	public Contact(Phonebook.Action action, String number, Integer id, Map<String, String> params) {
		this.params = params;
		this.number = number;
		this.id = id;
		this.action = action;
		initAction();
	}

	private void initAction() {
		this.params.put(this.action.getName(), this.number);

		if (id > 0) {
			params.put("id", id.toString());
			params.put(action.getName(), "");
		}
	}

	public static Contact[] parseContacts(String str) throws SmsapiException {

		class Parser {

			private String str;
			Contact[] contacts;

			Parser(String str) {
				this.str = str;
			}

			public Contact[] excecute() {
				try {

					JSONObject oData = new JSONObject(str);
					JSONArray aCont = oData.optJSONArray("contacts");

					if (aCont != null) {
						
						final int n = aCont.length();
						if (n > 0) {
							contacts = new Contact[n];
							for (int i = 0; i < n; i++) {
								JSONObject tmp = aCont.getJSONObject(i);
								contacts[i] = new Contact(bildValues(tmp));
							}
						} else {
							contacts = null;
						}
						
					} else {
						
						JSONObject oCont = oData.optJSONObject("contact");
						contacts = new Contact[1];

						if (oCont != null) {
							contacts[0] = new Contact(bildValues(oCont));
						} else {

							if (!oData.optString("error").isEmpty()) {
								contacts[0] = new Contact("").setError(SmsapiException.createError(oData.optString("error"), oData.optString("message")));
							} else if (!oData.optString("deleted").isEmpty() && oData.optString("deleted").equals("true")){
			                    contacts[0] = new Contact("").setDeletedFlag(true);
							}else {
								contacts[0] = new Contact(bildValues(oData));
							}
						}
					}

				} catch (JSONException ex) {
					throw new SmsapiException("Problem with decode json format");
				}
				return contacts;
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

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isError() {
		return (error != null);
	}

	private Contact setError(Object error) {
		this.error = error;
		return this;
	}
	
	private Contact setDeletedFlag(boolean val) {
		deleted = val;
		return this;
	}

	public String getNumber() {
		return number;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getInfo() {
		return info;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getCity() {
		return city;
	}

	public String getGender() {
		return gender;
	}

	public String getDateAdd() {
		return dateAdd;
	}

	public String getDateMod() {
		return dateMod;
	}

	public Object getError() {
		return error;
	}

	public Contact setFirstName(String firstName) {

		if (params != null) {
			params.put("first_name", firstName);
		}

		this.firstName = firstName;
		return this;
	}

	public Contact setLastName(String lastName) {

		if (params != null) {
			params.put("last_name", lastName);
		}

		this.lastName = lastName;
		return this;
	}

	public Contact setInfo(String info) {

		if (params != null) {
			params.put("info", info);
		}
		this.info = info;
		return this;
	}

	public Contact setEmail(String email) {

		if (params != null) {
			params.put("email", email);
		}
		this.email = email;
		return this;
	}

	public Contact setBirthday(String birthday) {

		if (params != null) {
			params.put("birthday", birthday);
		}
		this.birthday = birthday;
		return this;
	}

	public Contact setCity(String city) {

		if (params != null) {
			params.put("city", city);
		}

		this.city = city;
		return this;
	}

	public Contact setGender(Contact.Gender gender) {

		if (params != null) {
			params.put("gender", gender.type().toString());
		}

		this.gender = gender.name();
		return this;
	}
	
	public String[] getGroups()
	{
		String[] tmp = new String[groups.size()];
		int i = 0;
		for(String item: groups){
			tmp[i] = item;
			i++;
		}		
		return tmp;
	}
	
	public Contact setGroup(String name)
	{	
		if (params != null) {
			Integer size = groups.size();
			params.put("groups[" + size.toString() + "]", name);		
		}
		groups.add(name);
		return this;
	}
	
	public Contact setGroups(String[] names)
	{
		for(String name: names){
			setGroup(name);
		}
		
		return this;
	}
	
	public Contact setNumber(String number) {
		
		if(params != null){
			params.put("new_number",number);
		}
		
		this.number = number;
		
		return this;
	}
}
