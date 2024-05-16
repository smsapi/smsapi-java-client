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
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactsJsonMother.create());
        ContactsList action = new ContactsList();
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("GET", requestStub.requestMethod);
        assertEquals("contacts", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
