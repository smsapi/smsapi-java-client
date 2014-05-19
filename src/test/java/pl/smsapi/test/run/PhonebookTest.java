package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.PhonebookFactory;
import pl.smsapi.api.action.phonebook.*;
import pl.smsapi.api.response.*;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.SmsapiTest;

import java.util.GregorianCalendar;

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
    @Ignore
    public void groupAdd() throws SmsapiException {

        PhonebookGroupAdd action = apiFactory.actionGroupAdd(groupTest);
        GroupResponse item = action.execute();

        System.out.println("GroupAdd:");

        renderGroupItem(item);

    }

    @Test
    @Ignore
    public void groupEdit() throws SmsapiException {

        PhonebookGroupEdit action = apiFactory.actionGroupEdit(groupTest);
        action.setName(groupTestEdit).setInfo("to jest grupa testowa");
        GroupResponse item = action.execute();

        System.out.println("GroupEdit:");

        renderGroupItem(item);

    }

    @Test
    @Ignore
    public void groupGet() throws SmsapiException {

        PhonebookGroupGet action = apiFactory.actionGroupGet(groupTestEdit);
        GroupResponse item = action.execute();

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
    @Ignore
    public void contactAdd() throws SmsapiException {

        PhonebookContactAdd action = apiFactory.actionContactAdd(contactTest);

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

        ContactResponse item = action.execute();

        System.out.println("ContactAdd:");

        renderContactItem(item);

    }

    @Test
    @Ignore
    public void contactEdit() throws SmsapiException {

        PhonebookContactEdit action = apiFactory.actionContactEdit(contactTest);
        action.setNumber(contactTestEdit).setFirstName("Lolek");

        ContactResponse item = action.execute();

        System.out.println("ContactEdit:");

        renderContactItem(item);

    }

    @Test
    @Ignore
    public void contactGet() throws SmsapiException {

        PhonebookContactGet action = apiFactory.actionContactGet(contactTestEdit);

        ContactResponse item = action.execute();

        System.out.println("ContactGet:");

        renderContactItem(item);

    }

    @Test
    @Ignore
    public void contactDelete() throws SmsapiException {

        PhonebookContactDelete action = apiFactory.actionContactDelete(contactTestEdit);

        RawResponse item = action.execute();

        System.out.println("ContactDelete:");

        if (item != null) {
            System.out.println("Contact: " + item.getText());
        } else {
            System.out.println("Item null");
        }

    }

    @Test
    @Ignore
    public void groupDelete() throws SmsapiException {

        PhonebookGroupDelete action = apiFactory.actionGroupDelete(groupTestEdit);

        RawResponse item = action.execute();

        System.out.println("GroupDelete:");

        if (item != null) {
            System.out.println("Group: " + item.getText());
        } else {
            System.out.println("Item null");
        }

    }

    @Test
    @Ignore
    public void contactList() throws SmsapiException {

        ContactsResponse result = apiFactory.actionContactList().execute();
        System.out.println("ContactList:");

        for (ContactResponse item : result.getList()) {
            renderContactItem(item);
        }
    }

    @Test
    @Ignore
    public void groupList() throws SmsapiException {

        GroupsResponse result = apiFactory.actionGroupList().execute();

        System.out.println("GroupList:");

        for (GroupResponse item : result.getList()) {
            renderGroupItem(item);
        }

    }
}
