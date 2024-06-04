package pl.smsapi.api.action.vms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.VmsFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
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
    public void vmsSendTts() throws SmsapiException {
        VMSSend action = apiFactory.actionSend()
                .setTts("to jest test")
                .setTo(numberTest);

        StatusResponse responseAdd = action.execute();

        assertNotNull(responseAdd);
        assertEquals(1, responseAdd.count);
        assertTrue(responseAdd.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void vmsSendFile() throws FileNotFoundException, SmsapiException {
        VMSSend action = apiFactory.actionSend()
                .setFile(new File("src/test/java/pl/smsapi/test/voice_small.wav"))
                .setTo(numberTest);

        StatusResponse responseAdd = action.execute();

        assertNotNull(responseAdd);
        assertEquals(1, responseAdd.count);
        assertTrue(responseAdd.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void vmsGet() throws SmsapiException {
        StatusResponse responseAdd = apiFactory.actionSend()
                .setTts("to jest test")
                .setTo(numberTest)
                .execute();

        Optional<MessageResponse> addMessageResponse = responseAdd.list.stream().findFirst();
        assertTrue(addMessageResponse.isPresent());

        VMSGet actionGet = apiFactory.actionGet().id(addMessageResponse.get().getId());
        StatusResponse responseGet = actionGet.execute();

        assertNotNull(responseGet);
        assertEquals(1, responseGet.count);
        assertTrue(responseGet.list.stream().anyMatch(getMessageResponse -> getMessageResponse.getNumber().equals(numberTest)));
    }

    @Test
    public void vmsDelete() throws SmsapiException {
        StatusResponse responseAdd = apiFactory.actionSend()
                .setTts("to jest test")
                .setTo(numberTest)
                .setDateSent((new Date().getTime() / 1000) + 120)
                .execute();

        Optional<MessageResponse> addMessageResponse = responseAdd.list.stream().findFirst();
        assertTrue(addMessageResponse.isPresent());

        VMSDelete actionDelete = apiFactory.actionDelete(addMessageResponse.get().getId());
        CountableResponse responseDelete = actionDelete.execute();

        assertNotNull(responseDelete);
        assertEquals(1, responseDelete.count);
    }
}