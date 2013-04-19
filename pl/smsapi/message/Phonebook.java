
package pl.smsapi.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pl.smsapi.SmsapiException;
import pl.smsapi.sender.Sender;
import pl.smsapi.sender.SenderHttp;


public final class Phonebook implements MessageInterface {

	private String username;
	private String password;
	private final HashMap<String, String> params = new HashMap<String, String>();
	private Sender sender;
	private String path = "phonebook.do";
	
	public static enum Action {
		GROUP_LIST("list_groups"), 
		GROUP_GET("get_group"), 
		GROUP_ADD("add_group"), 
		GROUP_EDIT("edit_group"), 
		GROUP_DELETE("delete_group"), 
		CONTACT_LIST("list_contacts"),
		CONTACT_GET("get_contact"),
		CONTACT_ADD("add_contact"), 
		CONTACT_EDIT("edit_contact"), 
		CONTACT_DELETE("delete_contact");
		
		private final String name;

		Action(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	};
	
	public String getPath() {
		return path;
	}


	public Phonebook() {
	}

	
	public Phonebook(Sender sender) {
		this.sender = sender;
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
	
	@Override
	public HashMap getParams() {
		return params;
	}

	@Override
	public String getUsername() {
		return username;
	}

	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public void setPassword(String password, boolean encodeMD5) {

		this.password = (encodeMD5 == true) ? Message.MD5Digest(password) : password;
	}
	
	public class ListGroup {
		
		protected ListGroup(){}
		
	    public void setWithGroupAll(boolean val){			
		  if(val){
			 params.put("with_group_all", "1"); 
		  }else{
			 params.remove("with_group_all");
		  }
		}	
	}
	
	public ListGroup getListGroup()
	{
		params.clear();
		params.put(Action.GROUP_LIST.getName(), "1");
		return new ListGroup();		
	}
	
	public Group getGroup(String groupName)
	{
		params.clear();
		return new Group(Action.GROUP_GET, groupName, 0, params);		
	}
	
	public Group getGroup(int id)
	{
		params.clear();
		return new Group(Action.GROUP_GET, null, id, params);		
	}
	
	public Group deleteGroup(String groupName)
	{
		params.clear();
		return new Group(Action.GROUP_DELETE, groupName, 0, params);		
	}
	
	public Group deleteGroup(int id)
	{
		params.clear();
		return new Group(Action.GROUP_DELETE, null, id, params);		
	}
	
	public Group deleteGroup(Group group)
	{
		return deleteGroup(group.getName());		
	}
	
	public Group addGroup(String name)
	{
		params.clear();
		return new Group(Action.GROUP_ADD, name, 0, params);	
	}
	
	public Group addGroup(String name, String info)
	{
		params.clear();
		return new Group(Action.GROUP_ADD, name, 0, params).setInfo(info);	
	}
	
	public Group addGroup(Group group)
	{
		params.clear();
		return new Group(Action.GROUP_ADD, group.getName(), 0, params).setInfo(group.getInfo());	
	}
	
	public Group editGroup(int id)
	{
		params.clear();
		return new Group(Action.GROUP_EDIT, null, id, params);	
	}
	
	public Group editGroup(String oldName, String newName, String info)
	{
		params.clear();
		return new Group(Action.GROUP_EDIT,oldName, 0, params).setName(newName).setInfo(info);	
	}
	
	public Group editGroup(Group oldGroup, Group newGroup)
	{
		return editGroup(oldGroup.getName(), newGroup.getName(), newGroup.getInfo());
	}
	
	
	public class ListContact {
		
		public final static int LIMIT_MAX = 200;
		
		protected ListContact(){}
		
		public ListContact setOrderBy(Contact.OrderBy orderBy)
		{
			params.put("order_by", orderBy.name().toLowerCase()); 
			return this;
		}
		
		public ListContact setOrderDir(Contact.OrderDir orderDir)
		{
			params.put("order_dir", orderDir.name().toLowerCase()); 
			return this;
		}
		
		public ListContact setLimit(Integer limit)
		{
			if(limit > LIMIT_MAX){
				throw new SmsapiException("Invalit value limit (max " + Integer.toString(LIMIT_MAX) + ")");
			}
			
			params.put("limit", limit.toString()); 
			return this;
		}
		
		public ListContact setOffset(Integer offset)
		{
			params.put("offset", offset.toString()); 
			return this;
		}
			
	}
	
	public ListContact getListContact()
	{
		params.clear();
		params.put(Action.CONTACT_LIST.getName(), "1");
		return new ListContact();		
	}
	
	public Contact getContact(String number)
	{
		params.clear();
		return new Contact(Action.CONTACT_GET, number, 0, params);		
	}
	
	public Contact getContact(int id)
	{
		params.clear();
		return new Contact(Action.CONTACT_GET, null, id, params);		
	}
	
	public Contact addContact(String number)
	{
		params.clear();	
		return new Contact(Action.CONTACT_ADD, number, 0, params);		
	}
	
	public Contact addContact(String number, Map<String, String> data)
	{
		params.clear();	
		Contact contact = new Contact(Action.CONTACT_ADD, number, 0, params);
		
		contact.setFirstName(data.get("first_name"));
		contact.setLastName(data.get("last_name"));
		contact.setEmail(data.get("email"));
		contact.setInfo(data.get("info"));
		contact.setCity(data.get("city"));
		contact.setBirthday(data.get("birthday"));
		contact.setGroup(data.get("group"));
		
		String gender = data.get("gender");
		
		if(gender != null && gender.equals(Contact.Gender.MALE.name())){
			contact.setGender(Contact.Gender.MALE);
		}else if(gender != null && gender.equals(Contact.Gender.FEMALE.name())){
			contact.setGender(Contact.Gender.FEMALE);
		}
	
		return contact;
	}
	
	public Contact addContact(Contact contact)
	{
		params.clear();	
		Contact tmpCt = new Contact(Action.CONTACT_ADD, contact.getNumber(), 0, params);
		
		bindContactData(tmpCt, contact);
		
		return tmpCt;
	}
	
	private void bindContactData(Contact insC, Contact outC){
		
		insC.setFirstName(outC.getFirstName());
		insC.setLastName(outC.getLastName());
		insC.setEmail(outC.getEmail());
		insC.setInfo(outC.getInfo());
		insC.setCity(outC.getCity());
		insC.setBirthday(outC.getBirthday());
		insC.setGroups(outC.getGroups());
		
		String gender = outC.getGender();
		
		if(gender != null && gender.equals(Contact.Gender.MALE.name())){
			insC.setGender(Contact.Gender.MALE);
		}else if(gender != null && gender.equals(Contact.Gender.FEMALE.name())){
			insC.setGender(Contact.Gender.FEMALE);
		}	
	}
	
	public Contact deleteContact(String number)
	{
		params.clear();
		return new Contact(Action.CONTACT_DELETE, number, 0, params);		
	}
	
	public Contact deleteContact(int id)
	{
		params.clear();
		return new Contact(Action.CONTACT_DELETE, null, id, params);		
	}
	
	public Contact editContact(String number)
	{
		params.clear();
		return new Contact(Action.CONTACT_EDIT, number, 0, params);	
	}
	
	public Contact editContact(String oldNumber, String newNumber)
	{
		params.clear();
		return new Contact(Action.CONTACT_EDIT,oldNumber, 0, params).setNumber(newNumber);
	}
	
	public Contact editContact(Contact oldContact, Contact newContact)
	{
		params.clear();
		Contact tmpCt = new Contact(Action.CONTACT_EDIT,oldContact.getNumber(), 0, params);
		bindContactData(tmpCt, newContact);
		
		if(!newContact.getNumber().isEmpty()){
			tmpCt.setNumber(newContact.getNumber());
		}
		
		return tmpCt;
	}
	
}
