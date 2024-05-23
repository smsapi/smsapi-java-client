package pl.smsapi.api.action.contacts.groups;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.response.RawResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GroupsFactoryTest extends TestSmsapi {

    GroupsFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new GroupsFactory(client, proxy);
    }

    @Test
    public void addContactGroup() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);

        GroupAdd actionAdd = apiFactory.actionAdd(groupName);
        Group responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(groupName, responseAdd.name);
        assertEquals("", responseAdd.description);
        assertNull(responseAdd.idx);
        assertNotNull(responseAdd.createdBy);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.permissions);
    }

    @Test
    public void addContactGroupWithOptionalFields() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);

        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
                .withDescription("add-group-test")
                .withIdx("idx");
        Group responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(groupName, responseAdd.name);
        assertEquals("add-group-test", responseAdd.description);
        assertEquals("idx", responseAdd.idx);
        assertNotNull(responseAdd.createdBy);
        assertNotNull(responseAdd.dateCreated);
        assertNotNull(responseAdd.dateUpdated);
        assertNotNull(responseAdd.permissions);
    }

    @Test
    public void getContactGroup() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
            .withDescription("get-group-test")
            .withIdx("idx");
        Group responseAdd = actionAdd.execute();

        GroupGet actionGet = apiFactory.actionGet(responseAdd.id);
        Group responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(groupName, responseGet.name);
        assertEquals("get-group-test", responseGet.description);
        assertEquals("idx", responseGet.idx);
        assertNotNull(responseGet.createdBy);
        assertNotNull(responseGet.dateCreated);
        assertNotNull(responseGet.dateUpdated);
        assertNotNull(responseGet.permissions);
    }

    @Test
    public void editContactGroup() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
            .withDescription("edit-group-test-1")
            .withIdx("idx-1");
        Group responseAdd = actionAdd.execute();

        groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupEdit actionEdit = apiFactory.actionEdit(responseAdd.id)
            .withName(groupName)
            .withDescription("edit-group-test-2")
            .withIdx("idx-2");
        Group responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(groupName, responseEdit.name);
        assertEquals("edit-group-test-2", responseEdit.description);
        assertEquals("idx-2", responseEdit.idx);
        assertNotNull(responseEdit.createdBy);
        assertNotNull(responseEdit.dateCreated);
        assertNotNull(responseEdit.dateUpdated);
        assertNotNull(responseEdit.permissions);
    }

    @Test
    public void deleteContactGroup() throws SmsapiException {
        GroupAdd actionAdd = apiFactory.actionAdd("smsapi-java-client-" + new Random().nextInt(100000));
        Group responseAdd = actionAdd.execute();

        GroupDelete actionDelete = apiFactory.actionDelete(responseAdd.id);
        RawResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
    }

    @Test
    public void listContactGroups() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupAdd actionAdd = apiFactory.actionAdd(groupName);
        actionAdd.execute();

        GroupsList actionList = apiFactory.actionList();
        Groups responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.count >= 1);
        assertTrue(responseList.list.stream().anyMatch(groupResponse -> groupResponse.name.equals(groupName)));
    }
}
