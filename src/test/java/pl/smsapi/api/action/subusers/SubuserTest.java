package pl.smsapi.api.action.subusers;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import static org.junit.Assert.*;

public class SubuserTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        SubuserGet action = new SubuserGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "  \"username\": \"example_username\"," +
            "  \"active\": true," +
            "  \"description\": \"Resource description\"," +
            "  \"points\": {" +
            "    \"from_account\": 123.12," +
            "    \"per_month\": 321.21" +
            "  }" +
            "}"
        ));

        Subuser response = action.execute();

        assertNotNull(response);
        assertEquals("0f0f0f0f0f0f0f0f0f0f0f0f", response.getId());
        assertEquals("example_username", response.getUsername());
        assertTrue(response.isActive());
        assertEquals("Resource description", response.getDescription());
        assertEquals(123.12, response.getPointsFromAccount(), 0.01);
        assertEquals(321.21, response.getPointsPerMonth(), 0.01);
    }
}
