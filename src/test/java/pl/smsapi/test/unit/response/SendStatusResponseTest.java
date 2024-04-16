package pl.smsapi.test.unit.response;

import org.junit.Test;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class SendStatusResponseTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        SMSSend action = new SMSSend();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"count\": 1," +
            "  \"length\": 7," +
            "  \"message\": \"Message\"," +
            "  \"parts\": 1," +
            "  \"list\": [" +
            "    {" +
            "      \"id\": \"7074294081650020450\"," +
            "      \"points\": 0.165," +
            "      \"number\": \"48327201200\"," +
            "      \"date_sent\": 1712570310," +
            "      \"submitted_number\": \"327201200\"," +
            "      \"status\": \"QUEUE\"," +
            "      \"error\": null," +
            "      \"idx\": \"example-user-provided-id-123\"," +
            "      \"parts\": 10" +
            "    }" +
            "  ]" +
            "}"
        ));

        SendStatusResponse response = action.execute();

        assertFalse(response.getList().isEmpty());
        assertEquals(1, response.getCount());

        Optional<MessageResponse> message1 = response.getList().stream().filter(
                contactResponse -> contactResponse.getId().equals("7074294081650020450")
        ).findFirst();
        assertTrue(message1.isPresent());
        assertEquals(0.165, message1.get().getPoints(), 0.001);
        assertEquals("48327201200", message1.get().getNumber());
        assertEquals("QUEUE", message1.get().getStatus());
        assertEquals("", message1.get().getError());
        assertEquals("example-user-provided-id-123", message1.get().getIdx());
    }
}
