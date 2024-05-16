package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContactGetTest {

    @Test
    public void executeGetContactRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactJsonMother.create());
        ContactGet action = new ContactGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("GET", requestStub.requestMethod);
        assertEquals("contacts/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
