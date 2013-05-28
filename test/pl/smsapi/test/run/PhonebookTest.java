package pl.smsapi.test.run;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.PhonebookFactory;
import pl.smsapi.api.action.phonebook.*;
import pl.smsapi.api.response.*;
import pl.smsapi.test.SmsapiTest;

public class PhonebookTest extends SmsapiTest {

	private String groupTest = "mytest";
	private String groupTestEdit = "mytestedit";
	private String contactTest = "694562829";
	private String contactTestEdit = "617234123";

	@Test
	//@Ignore
	public void groupAdd() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		GroupResponse item;
		GroupAdd action = smsApi.actionGroupAdd(groupTest);

		item = (GroupResponse) executeAction(action);

		System.out.println("GroupAdd:");

		renderGroupItem(item);

	}

	@Test
	//@Ignore
	public void groupEdit() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		GroupResponse item;
		GroupEdit action = smsApi.actionGroupEdit(groupTest);
		action.setName(groupTestEdit).setInfo("to jest grupa testowa");

		item = (GroupResponse) executeAction(action);

		System.out.println("GroupEdit:");

		renderGroupItem(item);

	}

	@Test
	//@Ignore
	public void groupGet() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		GroupResponse item;
		GroupGet action = smsApi.actionGroupGet(groupTestEdit);

		item = (GroupResponse) executeAction(action);

		System.out.println("GroupGet:");

		renderGroupItem(item);

	}

	private void renderGroupItem(GroupResponse item) {

		if (item != null) {
			System.out.println("Groupname: "
					+ item.getName()
					+ " Info: " + item.getInfo()
					+ " Numbers:" + item.getNumbers());
		} else {
			System.out.println("Number: ");
		}
	}

	private void renderContactItem(ContactResponse item) {

		if (item != null) {
			System.out.println("Number: " + item.getNumber()
					+ " FirstName: " + item.getFirstName()
					+ " LastName:" + item.getLastName()
					+ " City: " + item.getBirthday()
					+ " Birthday: " + item.getBirthday()
					+ " Info: " + item.getInfo()
					+ " Gender: " + item.getGender()
					+ " DateAdd: " + item.getDateAdd()
					+ " DateMod: " + item.getDateMod());
		} else {
			System.out.println("Number: ");
		}
	}

	@Test
	//@Ignore
	public void contactAdd() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		ContactResponse item;
		ContactAdd action = smsApi.actionContactAdd(contactTest);

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(1976, 2, 15);

		action.setFirstName("Bolek")
				.setLastName("Dzik")
				.setBirthday(cal)
				.setInfo("to jest test kontaktu")
				.setEmail("bolek@aaa.pl")
				.setCity("Gliwice")
				.setGender(ContactResponse.Gender.MALE)
				.setGroup(groupTestEdit);

		item = (ContactResponse) executeAction(action);

		System.out.println("ContactAdd:");

		renderContactItem(item);

	}

	@Test
	//@Ignore
	public void contactEdit() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		ContactResponse item;
		ContactEdit action = smsApi.actionContactEdit(contactTest);
		action.setNumber(contactTestEdit).setFirstName("Lolek");

		item = (ContactResponse) executeAction(action);

		System.out.println("ContactEdit:");

		renderContactItem(item);

	}

	@Test
	//@Ignore
	public void contactGet() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		ContactResponse item;
		ContactGet action = smsApi.actionContactGet(contactTestEdit);

		item = (ContactResponse) executeAction(action);

		System.out.println("ContactGet:");

		renderContactItem(item);

	}

	@Test
	//@Ignore
	public void contactDelete() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		RawResponse item;
		ContactDelete action = smsApi.actionContactDelete(contactTestEdit);

		item = (RawResponse) executeAction(action);

		System.out.println("ContactDelete:");

		if (item != null) {
			System.out.println("Contact: " + item.getText());
		} else {
			System.out.println("Item null");
		}

	}

	@Test
	//@Ignore
	public void groupDelete() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		RawResponse item;
		GroupDelete action = smsApi.actionGroupDelete(groupTestEdit);

		item = (RawResponse) executeAction(action);

		System.out.println("GroupDelete:");

		if (item != null) {
			System.out.println("Group: " + item.getText());
		} else {
			System.out.println("Item null");
		}

	}

	@Test
	//@Ignore
	public void contactList() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		ContactsResponse result;

		result = (ContactsResponse) executeAction(smsApi.actionContactList());

		System.out.println("ContactList:");

		for (ContactResponse item : result.getList()) {
			renderContactItem(item);
		}
	}

	@Test
	//@Ignore
	public void groupList() {

		PhonebookFactory smsApi = new PhonebookFactory(client());

		GroupsResponse result;

		result = (GroupsResponse) executeAction(smsApi.actionGroupList());

		System.out.println("GroupList:");

		for (GroupResponse item : result.getList()) {
			renderGroupItem(item);
		}

	}
}
