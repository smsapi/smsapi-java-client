package pl.smsapi.test.unit.response.contacts;

import org.junit.Test;
import pl.smsapi.api.action.contacts.ContactsGroupList;
import pl.smsapi.api.response.contacts.ContactsGroupListResponse;
import pl.smsapi.api.response.contacts.ContactsGroupResponse;
import pl.smsapi.api.response.contacts.ContactsPermissionResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ContactsGroupListResponseTest {

    @Test
    public void deserialize_empty_response() throws SmsapiException {
        ContactsGroupList action = new ContactsGroupList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "    \"collection\": []," +
            "    \"size\": 0" +
            "}"));

        ContactsGroupListResponse response = action.execute();

        assertTrue(response.getList().isEmpty());
        assertEquals(0, response.getCount());
    }

    @Test
    public void deserialize_non_empty_response() throws SmsapiException {
        ContactsGroupList action = new ContactsGroupList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            "    {" +
            "      \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "      \"name\": \"Example Group\"," +
            "      \"description\": \"Resource description\"," +
            "      \"contacts_count\": 0," +
            "      \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "      \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "      \"created_by\": \"example_username\"," +
            "      \"idx\": \"example-user-provided-id-123\"," +
            "      \"contact_expire_after\": 0," +
            "      \"permissions\": [" +
            "        {" +
            "          \"group_id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "          \"username\": \"example_username\"," +
            "          \"write\": false," +
            "          \"read\": false," +
            "          \"send\": false" +
            "        }" +
            "      ]" +
            "    }" +
            "  ]" +
            "}"
        ));

        ContactsGroupListResponse response = action.execute();

        assertFalse(response.getList().isEmpty());
        assertEquals(1, response.getCount());

        Optional<ContactsGroupResponse> group1 = response.getList().stream().filter(
                contactResponse -> contactResponse.getId().equals("0f0f0f0f0f0f0f0f0f0f0f0f")
        ).findFirst();
        assertTrue(group1.isPresent());
        assertEquals("Example Group", group1.get().getName());
        assertEquals("Resource description", group1.get().getDescription());
        assertEquals("2017-07-21T17:32:28Z", group1.get().getDateCreated());
        assertEquals("2017-07-21T17:32:28Z", group1.get().getDateUpdated());
        assertEquals("example_username", group1.get().getCreatedBy());
        assertEquals("example-user-provided-id-123", group1.get().getIdx());

        assertFalse(group1.get().getPermissions().getList().isEmpty());
        assertEquals(1, group1.get().getPermissions().getCount());

        Optional<ContactsPermissionResponse> permission1 = group1.get().getPermissions().getList().stream().filter(
                permissionResponse -> permissionResponse.getUsername().equals("example_username")
        ).findFirst();
        assertTrue(permission1.isPresent());
        assertFalse(permission1.get().isWrite());
        assertFalse(permission1.get().isRead());
        assertFalse(permission1.get().isSend());
    }
}
