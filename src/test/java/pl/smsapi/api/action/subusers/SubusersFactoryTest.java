package pl.smsapi.api.action.subusers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.response.Response;
import pl.smsapi.exception.SmsapiErrorException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

import static org.junit.Assert.*;

@Ignore
public class SubusersFactoryTest extends TestSmsapi {

    SubusersFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new SubusersFactory(client, proxy);
    }

    @Test
    public void addSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!")
            .withApiPassword("AnotherStrongPassword123!")
            .withDescription("Resource description")
            .withPointsFromAccount(11.11)
            .withPointsPerMonth(22.22);
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(username, responseAdd.username);
        assertFalse(responseAdd.active);
        assertEquals("Resource description", responseAdd.description);
        assertEquals(11.11, responseAdd.pointsFromAccount, 0.01);
        assertEquals(22.22, responseAdd.pointsPerMonth, 0.01);
    }

    @Test
    public void addSubuserAsActive() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!")
            .withApiPassword("AnotherStrongPassword123!")
            .asActive();
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertTrue(responseAdd.active);
    }

    @Test
    public void addSubuserAsInactive() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();

        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!")
            .withApiPassword("AnotherStrongPassword123!")
            .asInactive();
        Subuser responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertFalse(responseAdd.active);
    }

    @Test
    public void getSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserGet actionGet = apiFactory.actionGet(responseAdd.id);
        Subuser responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(responseAdd.id, responseGet.id);
        assertEquals(username, responseGet.username);
    }

    @Test
    public void editSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserEdit actionEdit = apiFactory.actionEdit(responseAdd.id)
            .withPassword("NewStrongPassword123!")
            .withApiPassword("NewAnotherStrongPassword123!!")
            .asActive()
            .withDescription("New resource description")
            .withPointsFromAccount(999.99)
            .withPointsPerMonth(111.11);
        Subuser responseEdit = actionEdit.execute();

        assertNotNull(responseEdit);
        assertEquals(responseAdd.id, responseEdit.id);
        assertEquals(username, responseEdit.username);
        assertTrue(responseEdit.active);
        assertEquals("New resource description", responseEdit.description);
        assertEquals(999.99, responseEdit.pointsFromAccount, 0.01);
        assertEquals(111.11, responseEdit.pointsPerMonth, 0.01);
    }

    @Test
    public void deleteSubuser() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        Subuser responseAdd = actionAdd.execute();

        SubuserDelete actionDelete = apiFactory.actionDelete(responseAdd.id);
        Response responseDelete = actionDelete.execute();

        assertNull(responseDelete);
    }

    @Test
    public void listSubusers() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "StrongPassword123!");
        actionAdd.execute();

        SubusersList actionList = apiFactory.actionList();
        Subusers responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.count >= 1);
        assertTrue(responseList.list.stream().anyMatch(subuserResponse -> subuserResponse.username.equals(username)));
    }

    @Test
    public void addSubuserWithError() throws SmsapiException {
        String username = "smsapi-java-client-" + new Random().nextLong();
        SubuserAdd actionAdd = apiFactory.actionAdd(username, "WeakPassword");
        boolean errorCatch = false;

        try {
            actionAdd.execute();
        } catch (SmsapiErrorException badRequest) {
            assertEquals("Your password is too weak (use combination of lowercase, uppercase and numeric characters)", badRequest.getMessage());
            assertEquals("invalid_domain_logic", badRequest.getError());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void getSubuserWithError() throws SmsapiException {
        boolean errorCatch = false;

        SubuserGet actionGet = apiFactory.actionGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        try {
            actionGet.execute();
        } catch (SmsapiErrorException notFound) {
            assertEquals("Not found", notFound.getMessage());
            assertEquals("not_found", notFound.getError());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }
}
