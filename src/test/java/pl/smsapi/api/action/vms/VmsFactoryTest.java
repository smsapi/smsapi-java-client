package pl.smsapi.api.action.vms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.VmsFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ActionException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@Ignore
public class VmsFactoryTest extends TestSmsapi {
    private final String numberTest = "48694562829";

    VmsFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new VmsFactory(client, proxy);
    }

    @Test
    public void sendVmsWithTts() throws SmsapiException {
        VMSSend action = apiFactory.actionSend(numberTest, "to jest test");

        StatusResponse responseAdd = action.execute();

        assertNotNull(responseAdd);
        assertEquals(1, responseAdd.count);
        assertTrue(responseAdd.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void sendVmsWithFile() throws FileNotFoundException, SmsapiException {
        VMSSend action = apiFactory.actionSend(numberTest, new File("src/test/java/pl/smsapi/test/voice_small.wav"));

        StatusResponse responseAdd = action.execute();

        assertNotNull(responseAdd);
        assertEquals(1, responseAdd.count);
        assertTrue(responseAdd.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void getVms() throws SmsapiException {
        StatusResponse responseAdd = apiFactory.actionSend(numberTest, "to jest test")
            .execute();

        Optional<MessageResponse> addMessageResponse = responseAdd.list.stream().findFirst();
        assertTrue(addMessageResponse.isPresent());

        VMSGet actionGet = apiFactory.actionGet(addMessageResponse.get().getId());
        StatusResponse responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(1, responseGet.count);
        assertTrue(responseGet.list.stream().anyMatch(getMessageResponse -> getMessageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void deleteVms() throws SmsapiException {
        StatusResponse responseAdd = apiFactory.actionSend(numberTest, "to jest test")
            .setDateSent((new Date().getTime() / 1000) + 120)
            .execute();

        Optional<MessageResponse> addMessageResponse = responseAdd.list.stream().findFirst();
        assertTrue(addMessageResponse.isPresent());

        VMSDelete actionDelete = apiFactory.actionDelete(addMessageResponse.get().getId());
        CountableResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
        assertEquals(1, responseDelete.count);
    }

    @Test
    public void sendVmsWithError() throws SmsapiException {
        boolean errorCatch = false;
        VMSSend actionSend = apiFactory.actionSend();

        try {
            actionSend.execute();
        } catch (ActionException badRequest) {
            assertEquals("Recipients list cannot be empty", badRequest.getMessage());
            assertEquals(13, badRequest.getCode());
        }

        try {
            actionSend.setTo(numberTest).execute();
        } catch (ActionException badRequest) {
            assertEquals("Set Content requires one of parameters (tts, file)! None of them given!", badRequest.getMessage());
            assertEquals(11, badRequest.getCode());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void getVmsWithError() throws SmsapiException {
        boolean errorCatch = false;
        VMSGet actionGet = apiFactory.actionGet();

        try {
            actionGet.execute();
        } catch (ActionException badRequest) {
            assertEquals("Recipients list cannot be empty", badRequest.getMessage());
            assertEquals(13, badRequest.getCode());
        }

        try {
            actionGet.id("not existing").execute();
        } catch (ActionException badRequest) {
            assertEquals("Not exists ID message", badRequest.getMessage());
            assertEquals(301, badRequest.getCode());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void deleteVmsWithError() throws SmsapiException {
        boolean errorCatch = false;
        VMSDelete actionDelete = apiFactory.actionDelete();

        try {
            actionDelete.execute();
        } catch (ActionException badRequest) {
            assertEquals("Recipients list cannot be empty", badRequest.getMessage());
            assertEquals(13, badRequest.getCode());
        }

        try {
            actionDelete.id("not existing").execute();
        } catch (ActionException badRequest) {
            assertEquals("Not exists ID message", badRequest.getMessage());
            assertEquals(301, badRequest.getCode());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }
}