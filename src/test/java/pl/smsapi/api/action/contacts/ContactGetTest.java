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
        ContactGet action = new ContactGet("0f0f0f0f0f0f0f0f0f0f0f0f");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("contacts/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(ContactGet action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
