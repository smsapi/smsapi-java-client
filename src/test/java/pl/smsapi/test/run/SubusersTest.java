package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.SubusersFactory;
import pl.smsapi.api.action.subusers.*;
import pl.smsapi.api.response.NoContentResponse;
import pl.smsapi.api.action.subusers.Subuser;
import pl.smsapi.api.action.subusers.Subusers;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

import static org.junit.Assert.*;

public class SubusersTest extends TestSmsapi {

    SubusersFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new SubusersFactory(client, proxy);
    }

    @Test
    public void addSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        actionAdd.setApiPassword("AnotherStrongPassword123!");
        actionAdd.setDescription("Resource description");
        actionAdd.setPointsFromAccount(11.11);
        actionAdd.setPointsPerMonth(22.22);
        actionAdd.setEmail("smsapi-java-client@example.com");
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(username, responseAdd.getUsername());
        assertFalse(responseAdd.isActive());
        assertEquals("Resource description", responseAdd.getDescription());
        assertEquals(11.11, responseAdd.getPointsFromAccount(), 0.01);
        assertEquals(22.22, responseAdd.getPointsPerMonth(), 0.01);
    }

    @Test
    public void addSubuserAsActive() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        actionAdd.setApiPassword("AnotherStrongPassword123!");
        actionAdd.setAsActive();
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertTrue(responseAdd.isActive());
    }

    @Test
    public void addSubuserAsInactive() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        actionAdd.setApiPassword("AnotherStrongPassword123!");
        actionAdd.setAsInactive();
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertFalse(responseAdd.isActive());
    }

    @Test
    public void getSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserGet actionGet = apiFactory.actionGet(responseAdd.getId());
        Subuser responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(responseAdd.getId(), responseGet.getId());
        assertEquals(username, responseGet.getUsername());
    }

    @Test
    public void editSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserEdit actionEdit = apiFactory.actionEdit(responseAdd.getId());
        actionEdit.setPassword("NewStrongPassword123!");
        actionEdit.setApiPassword("NewAnotherStrongPassword123!!");
        actionEdit.setAsActive();
        actionEdit.setDescription("New resource description");
        actionEdit.setPointsFromAccount(999.99);
        actionEdit.setPointsPerMonth(111.11);
        Subuser responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(responseAdd.getId(), responseEdit.getId());
        assertEquals(username, responseEdit.getUsername());
        assertTrue(responseEdit.isActive());
        assertEquals("New resource description", responseEdit.getDescription());
        assertEquals(999.99, responseEdit.getPointsFromAccount(), 0.01);
        assertEquals(111.11, responseEdit.getPointsPerMonth(), 0.01);
    }

    @Test
    public void deleteSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserDelete actionDelete = apiFactory.actionDelete(responseAdd.getId());
        NoContentResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
    }

    @Test
    public void listSubusers() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        actionAdd.execute();

        SubusersList actionList = apiFactory.actionList();
        Subusers responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.getCount() >= 1);
        assertTrue(responseList.getList().stream().anyMatch(subuserResponse -> subuserResponse.getUsername().equals(username)));
    }
}
