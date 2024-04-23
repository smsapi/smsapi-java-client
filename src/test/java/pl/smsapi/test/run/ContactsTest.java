package pl.smsapi.test.run;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.ContactsFactory;
import pl.smsapi.api.action.contacts.*;
import pl.smsapi.api.response.RawResponse;
import pl.smsapi.api.response.contacts.*;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
public class ContactsTest extends TestSmsapi {
    private String numberTest = "694562829";
    private String testGroupIdx = "testgroupidx";

    private ContactsFactory apiFactory;

    private ContactsContactResponse testContact;
    private ContactsGroupResponse testGroup;

    @Before
    public void before() throws SmsapiException {
        apiFactory = new ContactsFactory(getAuthorizationClient(), getProxy());

        deleteTestData();

        testContact = createTestContact();
        testGroup = createTestGroup();
    }

    @After
    public void after() throws SmsapiException {
        deleteTestData();
    }

    private void deleteTestData() throws SmsapiException {
        ContactsContactResponse testContact = findContactByPhoneNumber(numberTest);
        ContactsGroupResponse testGroup = findGroupByIdx(testGroupIdx);

        if (testContact != null) {
            deleteContact(testContact.getId());
        }

        if (testGroup != null) {
            deleteGroup(testGroup.getId());
        }
    }

    @Test
    public void testList() throws SmsapiException {
        ContactsContactListResponse result = apiFactory.actionContactList().execute();

        assertTrue(listContainsContact(result.getList(), testContact));
    }

    @Test
    public void testListWithFiltering() throws SmsapiException {
        ContactsContactList action = apiFactory.actionContactList();
        action.filterByPhoneNumber(numberTest);

        ContactsContactListResponse result = action.execute();

        assertTrue(listContainsContact(result.getList(), testContact));
    }

    @Test
    public void testListWithFilteringAfterDeletion() throws SmsapiException {
        ContactsContactResponse testContact = findContactByPhoneNumber(numberTest);
        assertNotNull(testContact);
        deleteContact(testContact.getId());

        ContactsContactList action = apiFactory.actionContactList();
        action.filterByPhoneNumber(numberTest);

        ContactsContactListResponse result = action.execute();

        assertFalse(listContainsContact(result.getList(), testContact));
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

    private RawResponse deleteContact(String contactId) throws SmsapiException {
        return apiFactory.actionContactDelete(contactId).execute();
    }

    private ContactsGroupResponse createTestGroup() throws SmsapiException {
        ContactsGroupAdd action = apiFactory.actionGroupAdd();

        action.setName("Test Group Name")
                .setDescription("This is a test group created by JUnit tests.")
                .setIdx(testGroupIdx);

        return action.execute();
    }

    private RawResponse deleteGroup(String groupId) throws SmsapiException {
        return apiFactory.actionGroupDelete(groupId).execute();
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

    private ContactsContactResponse findContactByPhoneNumber(String phoneNumber) throws SmsapiException {
        ContactsContactList action = apiFactory.actionContactList();
        action.filterByPhoneNumber(phoneNumber);

        ContactsContactListResponse result = action.execute();

        if (result.getList().isEmpty()) {
            return null;
        }

        return result.getList().get(0);
    }

    private ContactsGroupResponse findGroupByIdx(String idx) throws SmsapiException {
        ContactsGroupListResponse result = apiFactory.actionGroupList().execute();

        for (ContactsGroupResponse group : result.getList()) {
            if (group.getIdx().equals(idx)) {
                return group;
            }
        }

        return null;
    }
}
