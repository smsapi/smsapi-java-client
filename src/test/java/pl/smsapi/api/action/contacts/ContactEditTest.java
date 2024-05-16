package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContactEditTest {

    @Test
    public void executeEditContactRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactJsonMother.create());
        ContactEdit action = new ContactEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("contacts/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddContactWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactJsonMother.create());
        ContactEdit action = new ContactEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.setPhoneNumber("48327201200");
        action.setEmail("john.doe@example.com");
        action.setFirstName("John");
        action.setLastName("Doe");
        action.setGender("male");
        action.setBirthdayDate("2017-07-21");
        action.setDescription("Resource description");
        action.setCity("Example City");
        action.setSource("Example Source");

        action.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("contacts/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("phone_number", "48327201200");
        expectedRequestPayload.put("email", "john.doe@example.com");
        expectedRequestPayload.put("first_name", "John");
        expectedRequestPayload.put("last_name", "Doe");
        expectedRequestPayload.put("gender", "male");
        expectedRequestPayload.put("birthday_date", "2017-07-21");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("city", "Example City");
        expectedRequestPayload.put("source", "Example Source");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
