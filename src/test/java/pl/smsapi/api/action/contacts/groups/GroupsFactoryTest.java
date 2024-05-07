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

        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
            .setDescription("add-group-test")
            .setIdx("idx");
        Group responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(groupName, responseAdd.getName());
        assertEquals("add-group-test", responseAdd.getDescription());
        assertEquals("idx", responseAdd.getIdx());
        assertNotNull(responseAdd.getCreatedBy());
        assertNotNull(responseAdd.getDateCreated());
        assertNotNull(responseAdd.getDateUpdated());
        assertNotNull(responseAdd.getPermissions());
        assertNotNull(responseAdd.getPermissions());
    }

    @Test
    public void getContactGroup() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
            .setDescription("get-group-test")
            .setIdx("idx");
        Group responseAdd = actionAdd.execute();

        GroupGet actionGet = apiFactory.actionGet(responseAdd.getId());
        Group responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(groupName, responseGet.getName());
        assertEquals("get-group-test", responseGet.getDescription());
        assertEquals("idx", responseGet.getIdx());
        assertNotNull(responseGet.getCreatedBy());
        assertNotNull(responseGet.getDateCreated());
        assertNotNull(responseGet.getDateUpdated());
        assertNotNull(responseGet.getPermissions());
        assertNotNull(responseGet.getPermissions());
    }

    @Test
    public void editContactGroup() throws SmsapiException {
        String groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupAdd actionAdd = apiFactory.actionAdd(groupName)
            .setDescription("edit-group-test-1")
            .setIdx("idx-1");
        Group responseAdd = actionAdd.execute();

        groupName = "smsapi-java-client-" + new Random().nextInt(100000);
        GroupEdit actionEdit = apiFactory.actionEdit(responseAdd.getId())
            .setName(groupName)
            .setDescription("edit-group-test-2")
            .setIdx("idx-2");
        Group responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(groupName, responseEdit.getName());
        assertEquals("edit-group-test-2", responseEdit.getDescription());
        assertEquals("idx-2", responseEdit.getIdx());
        assertNotNull(responseEdit.getCreatedBy());
        assertNotNull(responseEdit.getDateCreated());
        assertNotNull(responseEdit.getDateUpdated());
        assertNotNull(responseEdit.getPermissions());
        assertNotNull(responseEdit.getPermissions());
    }

    @Test
    public void deleteContactGroup() throws SmsapiException {
        GroupAdd actionAdd = apiFactory.actionAdd("smsapi-java-client-" + new Random().nextInt(100000));
        Group responseAdd = actionAdd.execute();

        GroupDelete actionDelete = apiFactory.actionDelete(responseAdd.getId());
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
        assertTrue(responseList.getCount() >= 1);
        assertTrue(responseList.getList().stream().anyMatch(groupResponse -> groupResponse.getName().equals(groupName)));
    }
}
