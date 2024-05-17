package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContactsListTest {

    @Test
    public void executeGetContactRequest() throws SmsapiException {
        ContactsList action = new ContactsList();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("contacts", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(ContactsList action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactsJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
