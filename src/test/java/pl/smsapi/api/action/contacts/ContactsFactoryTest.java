package pl.smsapi.api.action.contacts;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.response.RawResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

import static org.junit.Assert.*;

public class ContactsFactoryTest extends TestSmsapi
{
    ContactsFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new ContactsFactory(client, proxy);
    }

    @Test
    public void addContact() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";

        ContactAdd actionAdd = apiFactory.actionAdd()
            .setEmail(emailAddress)
            .setFirstName("John")
            .setLastName("Doe")
            .setGender("male")
            .setBirthdayDate("2000-01-01")
            .setDescription("add-contact-test")
            .setCity("Gliwice")
            .setSource("add-contact-test-data");
        Contact responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(emailAddress, responseAdd.getEmail());
        assertEquals("John", responseAdd.getFirstName());
        assertEquals("Doe", responseAdd.getLastName());
        assertEquals("male", responseAdd.getGender());
        assertEquals("2000-01-01", responseAdd.getBirthdayDate());
        assertEquals("add-contact-test", responseAdd.getDescription());
        assertEquals("Gliwice", responseAdd.getCity());
        assertEquals("add-contact-test-data", responseAdd.getSource());
        assertNotNull(responseAdd.getDateCreated());
        assertNotNull(responseAdd.getDateUpdated());
        assertEquals("", responseAdd.getCountry());
        assertNotNull(responseAdd.getGroups());
    }

    @Test
    public void getContact() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
            .setEmail(emailAddress)
            .setFirstName("John")
            .setLastName("Doe")
            .setGender("male")
            .setBirthdayDate("2000-01-01")
            .setDescription("get-contact-test")
            .setCity("Gliwice")
            .setSource("get-contact-test-data");
        Contact responseAdd = actionAdd.execute();

        ContactGet actionGet = apiFactory.actionGet(responseAdd.getId());
        Contact responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(emailAddress, responseGet.getEmail());
        assertEquals("John", responseGet.getFirstName());
        assertEquals("Doe", responseGet.getLastName());
        assertEquals("male", responseGet.getGender());
        assertEquals("2000-01-01", responseGet.getBirthdayDate());
        assertEquals("get-contact-test", responseGet.getDescription());
        assertEquals("Gliwice", responseGet.getCity());
        assertEquals("get-contact-test-data", responseGet.getSource());
        assertNotNull(responseAdd.getDateCreated());
        assertNotNull(responseAdd.getDateUpdated());
        assertEquals("", responseAdd.getCountry());
        assertNotNull(responseAdd.getGroups());
    }

    @Test
    public void editContact() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
            .setEmail(emailAddress)
            .setFirstName("John")
            .setLastName("Doe")
            .setGender("male")
            .setBirthdayDate("2000-01-01")
            .setDescription("edit-contact-test-1")
            .setCity("Gliwice")
            .setSource("edit-contact-test-data-1");
        Contact responseAdd = actionAdd.execute();

        emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactEdit actionEdit = apiFactory.actionEdit(responseAdd.getId())
            .setEmail(emailAddress)
            .setFirstName("Mary")
            .setLastName("Rose")
            .setGender("female")
            .setBirthdayDate("1999-12-31")
            .setDescription("edit-contact-test-2")
            .setCity("Bytom")
            .setSource("edit-contact-test-data-2");

        Contact responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(emailAddress, responseEdit.getEmail());
        assertEquals("Mary", responseEdit.getFirstName());
        assertEquals("Rose", responseEdit.getLastName());
        assertEquals("female", responseEdit.getGender());
        assertEquals("1999-12-31", responseEdit.getBirthdayDate());
        assertEquals("edit-contact-test-2", responseEdit.getDescription());
        assertEquals("Bytom", responseEdit.getCity());
        assertEquals("edit-contact-test-data-2", responseEdit.getSource());
        assertNotNull(responseAdd.getDateCreated());
        assertNotNull(responseAdd.getDateUpdated());
        assertEquals("", responseAdd.getCountry());
        assertNotNull(responseAdd.getGroups());
    }

    @Test
    public void deleteContact() throws SmsapiException {
        ContactAdd actionAdd = apiFactory.actionAdd()
            .setEmail("smsapi-java-client-" + new Random().nextInt(100000) + "@example.com");
        Contact responseAdd = actionAdd.execute();

        ContactDelete actionDelete = apiFactory.actionDelete(responseAdd.getId());
        RawResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
    }

    @Test
    public void listContacts() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
                .setEmail(emailAddress);
        actionAdd.execute();

        ContactsList actionList = apiFactory.actionList();
        Contacts responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.getCount() >= 1);
        assertTrue(responseList.getList().stream().anyMatch(contactResponse -> contactResponse.getEmail().equals(emailAddress)));
    }
}