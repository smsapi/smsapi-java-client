package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.api.action.contacts.groups.Group;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ContactTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        ContactGet action = new ContactGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "  \"first_name\": \"John\"," +
            "  \"last_name\": \"Doe\"," +
            "  \"phone_number\": \"48327201200\"," +
            "  \"email\": \"john.doe@example.com\"," +
            "  \"gender\": \"undefined\"," +
            "  \"birthday_date\": \"2017-07-21\"," +
            "  \"description\": \"Resource description\"," +
            "  \"city\": \"Example City\"," +
            "  \"country\": \"Example Country\"," +
            "  \"source\": \"Example Source\"," +
            "  \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "  \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "  \"groups\": [" +
            "    {" +
            "       \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "       \"name\": \"Example Group\"," +
            "       \"description\": \"Resource description\"," +
            "       \"contacts_count\": 0," +
            "       \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "       \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "       \"created_by\": \"example_username\"," +
            "       \"idx\": \"example-user-provided-id-123\"," +
            "       \"contact_expire_after\": 0," +
            "    }" +
            "  ]" +
            " }"
        ));

        Contact response = action.execute();

        assertNotNull(response);
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("48327201200", response.getPhoneNumber());
        assertEquals("john.doe@example.com", response.getEmail());
        assertEquals("undefined", response.getGender());
        assertEquals("2017-07-21", response.getBirthdayDate());
        assertEquals("Resource description", response.getDescription());
        assertEquals("Example City", response.getCity());
        assertEquals("Example Country", response.getCountry());
        assertEquals("Example Source", response.getSource());
        assertEquals("2017-07-21T17:32:28Z", response.getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", response.getDateUpdated());
        assertEquals(1, response.getGroups().count);

        Optional<Group> group = response.getGroups().list.stream().filter(
                contactResponse -> contactResponse.getId().equals("0f0f0f0f0f0f0f0f0f0f0f0f")
        ).findFirst();
        assertTrue(group.isPresent());
        assertEquals("Example Group", group.get().getName());
        assertEquals("Resource description", group.get().getDescription());
        assertEquals("2017-07-21T17:32:28Z", group.get().getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", group.get().getDateUpdated());
        assertEquals("example_username", group.get().getCreatedBy());
        assertEquals("example-user-provided-id-123", group.get().getIdx());
    }
}
