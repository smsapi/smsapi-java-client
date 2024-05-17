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
        assertEquals("John", response.firstName);
        assertEquals("Doe", response.lastName);
        assertEquals("48327201200", response.phoneNumber);
        assertEquals("john.doe@example.com", response.email);
        assertEquals("undefined", response.gender);
        assertEquals("2017-07-21", response.birthdayDate);
        assertEquals("Resource description", response.description);
        assertEquals("Example City", response.city);
        assertEquals("Example Country", response.country);
        assertEquals("Example Source", response.source);
        assertEquals("2017-07-21T17:32:28Z", response.dateCreated);
        assertEquals("2017-07-21T17:32:28Z", response.dateUpdated);
        assertEquals(1, response.groups.count);

        Optional<Group> group = response.groups.list.stream().filter(
                contactResponse -> contactResponse.id.equals("0f0f0f0f0f0f0f0f0f0f0f0f")
        ).findFirst();
        assertTrue(group.isPresent());
        assertEquals("Example Group", group.get().name);
        assertEquals("Resource description", group.get().description);
        assertEquals("2017-07-21T17:32:28Z", group.get().dateCreated);
        assertEquals("2017-07-21T17:32:28Z", group.get().dateUpdated);
        assertEquals("example_username", group.get().createdBy);
        assertEquals("example-user-provided-id-123", group.get().idx);
    }
}
