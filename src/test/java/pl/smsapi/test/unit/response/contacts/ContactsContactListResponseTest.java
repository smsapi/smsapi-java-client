package pl.smsapi.test.unit.response.contacts;

import org.junit.Test;
import pl.smsapi.api.action.contacts.ContactsContactList;
import pl.smsapi.api.response.contacts.ContactsContactListResponse;
import pl.smsapi.api.response.contacts.ContactsContactResponse;
import pl.smsapi.api.response.contacts.ContactsGroupResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;

public class ContactsContactListResponseTest {

    @Test
    public void deserialize_empty_response() throws SmsapiException {
        ContactsContactList action = new ContactsContactList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "    \"collection\": []," +
            "    \"size\": 0" +
            "}"
        ));

        ContactsContactListResponse response = action.execute();

        assertTrue(response.getList().isEmpty());
        assertEquals(0, response.getCount());
    }

    @Test
    public void deserialize_non_empty_response() throws SmsapiException {
        ContactsContactList action = new ContactsContactList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            "    {" +
            "      \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "      \"first_name\": \"John\"," +
            "      \"last_name\": \"Doe\"," +
            "      \"phone_number\": \"48327201200\"," +
            "      \"email\": \"john.doe@example.com\"," +
            "      \"gender\": \"undefined\"," +
            "      \"birthday_date\": \"2017-07-21\"," +
            "      \"description\": \"Resource description\"," +
            "      \"city\": \"Example City\"," +
            "      \"country\": \"Example Country\"," +
            "      \"source\": \"Example Source\"," +
            "      \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "      \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "      \"groups\": [" +
            "        {" +
            "          \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "          \"name\": \"Example Group\"," +
            "          \"description\": \"Resource description\"," +
            "          \"contacts_count\": 0," +
            "          \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "          \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "          \"created_by\": \"example_username\"," +
            "          \"idx\": \"example-user-provided-id-123\"," +
            "          \"contact_expire_after\": 0," +
            "        }" +
            "      ]" +
            "    }" +
            "  ]" +
            "}"
        ));

        ContactsContactListResponse response = action.execute();

        assertFalse(response.getList().isEmpty());
        assertEquals(1, response.getCount());

        Optional<ContactsContactResponse> contact1 = response.getList().stream().filter(
            contactResponse -> contactResponse.getId().equals("0f0f0f0f0f0f0f0f0f0f0f0f")
        ).findFirst();
        assertTrue(contact1.isPresent());
        assertEquals("John", contact1.get().getFirstName());
        assertEquals("Doe", contact1.get().getLastName());
        assertEquals("48327201200", contact1.get().getPhoneNumber());
        assertEquals("john.doe@example.com", contact1.get().getEmail());
        assertEquals("undefined", contact1.get().getGender());
        assertEquals("2017-07-21", contact1.get().getBirthdayDate());
        assertEquals("Resource description", contact1.get().getDescription());
        assertEquals("Example City", contact1.get().getCity());
        assertEquals("Example Country", contact1.get().getCountry());
        assertEquals("Example Source", contact1.get().getSource());
        assertEquals("2017-07-21T17:32:28Z", contact1.get().getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", contact1.get().getDateUpdated());
        assertEquals(1, contact1.get().getGroups().getCount());

        Optional<ContactsGroupResponse> group1 = contact1.get().getGroups().getList().stream().filter(
                contactResponse -> contactResponse.getId().equals("0f0f0f0f0f0f0f0f0f0f0f0f")
        ).findFirst();
        assertTrue(group1.isPresent());
        assertEquals("Example Group", group1.get().getName());
        assertEquals("Resource description", group1.get().getDescription());
        assertEquals("2017-07-21T17:32:28Z", group1.get().getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", group1.get().getDateUpdated());
        assertEquals("example_username", group1.get().getCreatedBy());
        assertEquals("example-user-provided-id-123", group1.get().getIdx());
    }
}
