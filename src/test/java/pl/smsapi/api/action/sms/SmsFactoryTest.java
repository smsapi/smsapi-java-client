package pl.smsapi.api.action.sms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ActionException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@Ignore
public class SmsFactoryTest extends TestSmsapi {

    SmsFactory apiFactory;
    private final String numberTest = "48694562829";

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new SmsFactory(client, proxy);
    }

    @Test
    public void sendSms() throws SmsapiException {
        SMSSend actionSend = apiFactory.actionSend()
                .setText("test message")
                .setTo(numberTest);

        SendStatusResponse responseAdd = actionSend.execute();

        assertNotNull(responseAdd);
        assertEquals(1, responseAdd.count);
        assertTrue(responseAdd.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void getSms() throws SmsapiException {
        SendStatusResponse responseSend = apiFactory.actionSend()
                .setText("test message")
                .setTo(numberTest)
                .execute();

        Optional<MessageResponse> sendMessageResponse = responseSend.list.stream().findFirst();
        assertTrue(sendMessageResponse.isPresent());

        SMSGet actionGet = apiFactory.actionGet().id(sendMessageResponse.get().getId());
        StatusResponse responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(1, responseGet.count);
        assertTrue(responseGet.list.stream().anyMatch(getMessageResponse -> getMessageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void deleteSms() throws SmsapiException {
        SendStatusResponse responseSend = apiFactory.actionSend()
                .setText("test message")
                .setTo(numberTest)
                .setDateSent((new Date().getTime() / 1000) + 120)
                .execute();

        Optional<MessageResponse> sendMessageResponse = responseSend.list.stream().findFirst();
        assertTrue(sendMessageResponse.isPresent());

        SMSDelete actionDelete = apiFactory.actionDelete().id(sendMessageResponse.get().getId());
        CountableResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
        assertEquals(1, responseDelete.count);
    }

    @Test
    public void sendSmsWithoutRecipientsListError() throws SmsapiException {
        SMSSend action = apiFactory.actionSend();
        boolean errorCatch = false;

        try {
            action.execute();
        } catch (ActionException badRequest) {
            assertEquals("Recipients list cannot be empty", badRequest.getMessage());
            assertEquals(13, badRequest.getCode());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }

    @Test
    public void sendSmsWithoutMessageError() throws SmsapiException {
        SMSSend action = apiFactory.actionSend()
            .setText("")
            .setTo(numberTest);
        boolean errorCatch = false;

        try {
            action.execute();
        } catch (ActionException badRequest) {
            assertEquals("Content is empty", badRequest.getMessage());
            assertEquals(11, badRequest.getCode());
            errorCatch = true;
        }

        assertTrue(errorCatch);
    }
}
