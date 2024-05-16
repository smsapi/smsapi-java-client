package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GroupTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        GroupGet action = new GroupGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "   \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "   \"name\": \"Example Group\"," +
            "   \"description\": \"Resource description\"," +
            "   \"contacts_count\": 0," +
            "   \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "   \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "   \"created_by\": \"example_username\"," +
            "   \"idx\": \"example-user-provided-id-123\"," +
            "   \"contact_expire_after\": 0," +
            "   \"permissions\": [" +
            "     {" +
            "       \"group_id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "       \"username\": \"example_username\"," +
            "       \"write\": false," +
            "       \"read\": false," +
            "       \"send\": false" +
            "     }" +
            "   ]" +
            "}"
        ));

        Group response = action.execute();

        assertNotNull(response);
        assertEquals("Example Group", response.getName());
        assertEquals("Resource description", response.getDescription());
        assertEquals("2017-07-21T17:32:28Z", response.getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", response.getDateUpdated());
        assertEquals("example_username", response.getCreatedBy());
        assertEquals("example-user-provided-id-123", response.getIdx());

        assertFalse(response.getPermissions().list.isEmpty());
        assertEquals(1, response.getPermissions().count);

        Optional<Permission> permission1 = response.getPermissions().list.stream().filter(
                permissionResponse -> permissionResponse.getUsername().equals("example_username")
        ).findFirst();
        assertTrue(permission1.isPresent());
        assertFalse(permission1.get().isWrite());
        assertFalse(permission1.get().isRead());
        assertFalse(permission1.get().isSend());
    }
}
