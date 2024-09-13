package pl.smsapi.api.action.sms.sendernames;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.response.RawResponse;
import pl.smsapi.exception.SmsapiErrorException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@Ignore
public class SendernamesFactoryTest extends TestSmsapi {

    SendernamesFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new SendernamesFactory(client, proxy);
    }

    @Test
    public void addSendername() throws SmsapiException {
        String sender = SenderMother.createRandomValid();

        SendernameAdd actionAdd = apiFactory.actionAdd(sender);
        Sendername responseAdd = actionAdd.execute();

        assertNotNull(responseAdd);
        assertEquals(sender, responseAdd.sender);
        assertNotNull(responseAdd.status);
        assertFalse(responseAdd.isDefault);
        assertNotNull(responseAdd.createdAt);
    }

    @Test
    public void addSendernameWithError() throws SmsapiException {
        String sender = SenderMother.createInvalid();
        SendernameAdd actionAdd = apiFactory.actionAdd(sender);
        boolean errorCatch = false;

        try {
            actionAdd.execute();
        } catch (SmsapiErrorException badRequest) {
            assertEquals("Sendername is not valid", badRequest.getMessage());
            assertEquals("invalid_sender", badRequest.getError());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void deleteSendername() throws SmsapiException {
        SendernameAdd actionAdd = apiFactory.actionAdd(SenderMother.createRandomValid());
        Sendername responseAdd = actionAdd.execute();

        SendernameDelete actionDelete = apiFactory.actionDelete(responseAdd.sender);
        RawResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
    }

    @Test
    public void makeDefaultSendername() throws SmsapiException {
        SendernameAdd actionAdd = apiFactory.actionAdd(SenderMother.createRandomValid());
        Sendername responseAdd = actionAdd.execute();

        SendernameMakeDefault actionMakeDefault = apiFactory.actionMakeDefault(responseAdd.sender);
        boolean errorCatch = false;

        try {
            actionMakeDefault.execute();
        } catch (SmsapiErrorException badRequest) {
            assertEquals("Cannot set to default not active sendername", badRequest.getMessage());
            assertEquals("sender_not_active", badRequest.getError());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void listSendernames() throws SmsapiException {
        String sender = SenderMother.createRandomValid();
        SendernameAdd actionAdd = apiFactory.actionAdd(sender);
        actionAdd.execute();

        SendernamesList actionList = apiFactory.actionList();
        Sendernames responseList = actionList.execute();

        assertNotNull(responseList);
        assertTrue(responseList.count >= 1);
        assertTrue(responseList.list.stream().anyMatch(sendernameResponse -> sendernameResponse.sender.equals(sender)));
    }

    private static class SenderMother {

        static String createRandomValid() {
            return "test-" + new Random().nextInt(100000);
        }

        static String createInvalid() {
            return "test-too-long-sender";
        }
    }
}
