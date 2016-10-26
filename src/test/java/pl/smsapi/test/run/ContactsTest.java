package pl.smsapi.test.run;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.ContactsFactory;
import pl.smsapi.api.action.contacts.ContactsContactAdd;
import pl.smsapi.api.action.contacts.ContactsContactEdit;
import pl.smsapi.api.action.contacts.ContactsGroupAdd;
import pl.smsapi.api.action.contacts.ContactsGroupEdit;
import pl.smsapi.api.response.RawResponse;
import pl.smsapi.api.response.contacts.*;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactsTest extends TestSmsapi {
    private String numberTest = "694562829";

    private ContactsFactory apiFactory;

    private ContactsContactResponse testContact;
    private ContactsGroupResponse testGroup;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new ContactsFactory(getAuthorizationClient());

        try {
            testContact = createTestContact();
            testGroup = createTestGroup();
        } catch (SmsapiException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Override
    @After
    public void tearDown() {
        super.tearDown();

        try {
            deleteContact(testContact);
            deleteGroup(testGroup);
        } catch (SmsapiException e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    @Test
    public void testList() throws SmsapiException {
        ContactsContactListResponse result = apiFactory.actionContactList().execute();

        assertTrue(listContainsContact(result.getList(), testContact));
    }

    @Test
    public void testGet() throws SmsapiException {
        ContactsContactResponse result = apiFactory.actionContactGet(testContact.getId()).execute();

        assertEquals(testContact.getId(), result.getId());
    }

    @Test
    public void testEdit() throws SmsapiException {
        String anotherTestName = "Another test name";

        ContactsContactEdit action = apiFactory.actionContactEdit(testContact.getId());
        action.setFirstName(anotherTestName);
        ContactsContactResponse result = action.execute();

        assertEquals(anotherTestName, result.getFirstName());
    }

    @Test
    public void testListGroup() throws SmsapiException {
        ContactsGroupListResponse result = apiFactory.actionGroupList().execute();

        assertTrue(listContainsGroup(result.getList(), testGroup));
    }

    @Test
    public void testGetGroup() throws SmsapiException {
        ContactsGroupResponse result = apiFactory.actionGroupGet(testGroup.getId()).execute();

        assertEquals(testGroup.getName(), result.getName());
    }

    @Test
    public void testEditGroup() throws SmsapiException {
        String anotherTestName = "Another test name";

        ContactsGroupEdit action = apiFactory.actionGroupEdit(testGroup.getId());
        action.setName(anotherTestName);
        ContactsGroupResponse result = action.execute();

        assertEquals(anotherTestName, result.getName());
    }

    private ContactsContactResponse createTestContact() throws SmsapiException {
        ContactsContactAdd action = apiFactory.actionContactAdd();

        action.setFirstName("Test First Name")
                .setLastName("Test Last Name")
                .setBirthdayDate("1960-01-01")
                .setPhoneNumber(numberTest)
                .setEmail("test@example.com")
                .setGender(ContactsContactResponse.Gender.MALE)
                .setCity("Gliwice");

        return action.execute();
    }

    private RawResponse deleteContact(ContactsContactResponse contact) throws SmsapiException {
        return apiFactory.actionContactDelete(contact.getId()).execute();
    }

    private ContactsGroupResponse createTestGroup() throws SmsapiException {
        ContactsGroupAdd action = apiFactory.actionGroupAdd();

        action.setName("Test group")
                .setDescription("This is a test group created by JUnit tests.")
                .setIdx("testgroupidx");

        return action.execute();
    }

    private RawResponse deleteGroup(ContactsGroupResponse group) throws SmsapiException {
        return apiFactory.actionGroupDelete(group.getId()).execute();
    }

    private boolean listContainsContact(List<ContactsContactResponse> contactList, ContactsContactResponse testedContact) {
        for (ContactsContactResponse contact : contactList) {
            if (contact.getId().equals(testedContact.getId())) {
                return true;
            }
        }

        return false;
    }

    private boolean listContainsGroup(List<ContactsGroupResponse> groupList, ContactsGroupResponse testedGroup) {
        for (ContactsGroupResponse group : groupList) {
            if (group.getId().equals(testedGroup.getId())) {
                return true;
            }
        }

        return false;
    }
}
