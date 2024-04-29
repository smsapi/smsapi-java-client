package pl.smsapi.test.unit.response.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubusersList;
import pl.smsapi.api.response.subusers.SubusersResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;
import pl.smsapi.test.unit.action.subusers.SubuserResponseJsonMother;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SubusersResponseTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        SubusersList action = new SubusersList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            SubuserResponseJsonMother.create() +
            "  ]" +
            "}"
        ));

        SubusersResponse response = action.execute();

        assertNotNull(response);
        assertEquals(1, response.getCount());
        assertEquals(1, response.getList().size());
    }
}
