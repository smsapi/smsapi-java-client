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
            .withEmail(emailAddress);
        Contact responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(emailAddress, responseAdd.email);
        assertNull(responseAdd.phoneNumber);
        assertEquals("undefined", responseAdd.gender);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.groups);
        assertNull(responseAdd.country);
        assertNull(responseAdd.firstName);
        assertNull(responseAdd.lastName);
        assertNull(responseAdd.birthdayDate);
        assertNull(responseAdd.description);
        assertNull(responseAdd.city);
        assertNull(responseAdd.source);
    }

    @Test
    public void addContactWithOptionalFields() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";

        ContactAdd actionAdd = apiFactory.actionAdd()
            .withEmail(emailAddress)
            .withFirstName("John")
            .withLastName("Doe")
            .withGender("male")
            .withBirthdayDate("2000-01-01")
            .withDescription("add-contact-test")
            .withCity("Gliwice")
            .withSource("add-contact-test-data");
        Contact responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(emailAddress, responseAdd.email);
        assertNull(responseAdd.phoneNumber);
        assertEquals("male", responseAdd.gender);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.groups);
        assertNull(responseAdd.country);
        assertEquals("John", responseAdd.firstName);
        assertEquals("Doe", responseAdd.lastName);
        assertEquals("2000-01-01", responseAdd.birthdayDate);
        assertEquals("add-contact-test", responseAdd.description);
        assertEquals("Gliwice", responseAdd.city);
        assertEquals("add-contact-test-data", responseAdd.source);
    }

    @Test
    public void getContact() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
            .withEmail(emailAddress)
            .withFirstName("John")
            .withLastName("Doe")
            .withGender("male")
            .withBirthdayDate("2000-01-01")
            .withDescription("get-contact-test")
            .withCity("Gliwice")
            .withSource("get-contact-test-data");
        Contact responseAdd = actionAdd.execute();

        ContactGet actionGet = apiFactory.actionGet(responseAdd.id);
        Contact responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(emailAddress, responseGet.email);
        assertNull(responseAdd.phoneNumber);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.groups);
        assertNull(responseAdd.country);
        assertEquals("John", responseGet.firstName);
        assertEquals("Doe", responseGet.lastName);
        assertEquals("male", responseGet.gender);
        assertEquals("2000-01-01", responseGet.birthdayDate);
        assertEquals("get-contact-test", responseGet.description);
        assertEquals("Gliwice", responseGet.city);
        assertEquals("get-contact-test-data", responseGet.source);
    }

    @Test
    public void editContact() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
            .withEmail(emailAddress)
            .withFirstName("John")
            .withLastName("Doe")
            .withGender("male")
            .withBirthdayDate("2000-01-01")
            .withDescription("edit-contact-test-1")
            .withCity("Gliwice")
            .withSource("edit-contact-test-data-1");
        Contact responseAdd = actionAdd.execute();

        emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactEdit actionEdit = apiFactory.actionEdit(responseAdd.id)
            .withEmail(emailAddress)
            .withFirstName("Mary")
            .withLastName("Rose")
            .withGender("female")
            .withBirthdayDate("1999-12-31")
            .withDescription("edit-contact-test-2")
            .withCity("Bytom")
            .withSource("edit-contact-test-data-2");

        Contact responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(emailAddress, responseEdit.email);
        assertNull(responseAdd.phoneNumber);
        assertEquals("female", responseEdit.gender);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.groups);
        assertNull(responseAdd.country);
        assertEquals("Mary", responseEdit.firstName);
        assertEquals("Rose", responseEdit.lastName);
        assertEquals("1999-12-31", responseEdit.birthdayDate);
        assertEquals("edit-contact-test-2", responseEdit.description);
        assertEquals("Bytom", responseEdit.city);
        assertEquals("edit-contact-test-data-2", responseEdit.source);
    }

    @Test
    public void deleteContact() throws SmsapiException {
        ContactAdd actionAdd = apiFactory.actionAdd()
            .withEmail("smsapi-java-client-" + new Random().nextInt(100000) + "@example.com");
        Contact responseAdd = actionAdd.execute();

        ContactDelete actionDelete = apiFactory.actionDelete(responseAdd.id);
        RawResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
    }

    @Test
    public void listContacts() throws SmsapiException {
        String emailAddress = "smsapi-java-client-" + new Random().nextInt(100000) + "@example.com";
        ContactAdd actionAdd = apiFactory.actionAdd()
                .withEmail(emailAddress);
        actionAdd.execute();

        ContactsList actionList = apiFactory.actionList();
        Contacts responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.count >= 1);
        assertTrue(responseList.list.stream().anyMatch(contactResponse -> contactResponse.email.equals(emailAddress)));
    }
}
