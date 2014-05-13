package pl.smsapi.test.run;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.MmsFactory;
import pl.smsapi.api.PhonebookFactory;
import pl.smsapi.api.action.phonebook.*;
import pl.smsapi.api.response.*;
import pl.smsapi.test.SmsapiTest;

public class PhonebookTest extends SmsapiTest {

	private String groupTest = "mytest";
	private String groupTestEdit = "mytestedit";
	private String contactTest = "694562829";
	private String contactTestEdit = "617234123";

    PhonebookFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new PhonebookFactory(getAuthorizationClient(), getProxy());
    }

	@Test
	//@Ignore
	public void groupAdd() {

		GroupResponse item;
		GroupAdd action = apiFactory.actionGroupAdd(groupTest);

		item = (GroupResponse) executeAction(action);

		System.out.println("GroupAdd:");

		renderGroupItem(item);

	}

	@Test
	//@Ignore
	public void groupEdit() {

		GroupResponse item;
		GroupEdit action = apiFactory.actionGroupEdit(groupTest);
		action.setName(groupTestEdit).setInfo("to jest grupa testowa");

		item = (GroupResponse) executeAction(action);

		System.out.println("GroupEdit:");

		renderGroupItem(item);

	}

	@Test
	//@Ignore
	public void groupGet() {

		GroupResponse item;
		GroupGet action = apiFactory.actionGroupGet(groupTestEdit);

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

		ContactResponse item;
		ContactAdd action = apiFactory.actionContactAdd(contactTest);

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

		ContactResponse item;
		ContactEdit action = apiFactory.actionContactEdit(contactTest);
		action.setNumber(contactTestEdit).setFirstName("Lolek");

		item = (ContactResponse) executeAction(action);

		System.out.println("ContactEdit:");

		renderContactItem(item);

	}

	@Test
	//@Ignore
	public void contactGet() {

		ContactResponse item;
		ContactGet action = apiFactory.actionContactGet(contactTestEdit);

		item = (ContactResponse) executeAction(action);

		System.out.println("ContactGet:");

		renderContactItem(item);

	}

	@Test
	//@Ignore
	public void contactDelete() {

		RawResponse item;
		ContactDelete action = apiFactory.actionContactDelete(contactTestEdit);

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

		RawResponse item;
		GroupDelete action = apiFactory.actionGroupDelete(groupTestEdit);

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

		ContactsResponse result;

		result = (ContactsResponse) executeAction(apiFactory.actionContactList());

		System.out.println("ContactList:");

		for (ContactResponse item : result.getList()) {
			renderContactItem(item);
		}
	}

	@Test
	//@Ignore
	public void groupList() {

		GroupsResponse result;

		result = (GroupsResponse) executeAction(apiFactory.actionGroupList());

		System.out.println("GroupList:");

		for (GroupResponse item : result.getList()) {
			renderGroupItem(item);
		}

	}
}
