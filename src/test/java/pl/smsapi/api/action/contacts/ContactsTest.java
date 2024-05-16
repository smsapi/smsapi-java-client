package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContactsTest {

    @Test
    public void deserialize_response() throws SmsapiException {
        ContactsList action = new ContactsList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            ContactJsonMother.create() +
            "  ]" +
            "}"
        ));

        Contacts response = action.execute();

        assertNotNull(response);
        assertEquals(1, response.count);
        assertEquals(1, response.list.size());
    }
}
