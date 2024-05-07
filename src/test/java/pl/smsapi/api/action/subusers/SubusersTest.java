package pl.smsapi.api.action.subusers;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SubusersTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        SubusersList action = new SubusersList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            SubuserJsonMother.create() +
            "  ]" +
            "}"
        ));

        Subusers response = action.execute();

        assertNotNull(response);
        assertEquals(1, response.count);
        assertEquals(1, response.list.size());
    }
}
