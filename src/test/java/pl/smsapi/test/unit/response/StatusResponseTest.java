package pl.smsapi.test.unit.response;

import org.junit.Test;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StatusResponseTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        SMSGet action = new SMSGet("7074294081650020450");
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "    \"count\": 1," +
            "    \"list\": [" +
            "        {" +
            "            \"error\": null," +
            "            \"id\": \"7074294081650020450\"," +
            "            \"idx\": \"example-user-provided-id-123\"," +
            "            \"number\": \"48327201200\"," +
            "            \"points\": 0.165," +
            "            \"status\": \"QUEUE\"" +
            "        }" +
            "    ]" +
            "}"
        ));

        StatusResponse response = action.execute();

        assertFalse(response.list.isEmpty());
        assertEquals(1, response.count);

        Optional<MessageResponse> message1 = response.list.stream().filter(
            messageResponse -> messageResponse.getId().equals("7074294081650020450")
        ).findFirst();
        assertTrue(message1.isPresent());
        assertEquals(0.165, message1.get().getPoints(), 0.001);
        assertEquals("48327201200", message1.get().getNumber());
        assertEquals("QUEUE", message1.get().getStatus());
        assertEquals("", message1.get().getError());
        assertEquals("example-user-provided-id-123", message1.get().getIdx());
        assertNull(message1.get().getToBeSentAtTimestamp());
    }
}
