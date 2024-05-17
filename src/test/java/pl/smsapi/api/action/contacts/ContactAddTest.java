package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContactAddTest {

    @Test
    public void executeAddPhoneContactRequest() throws SmsapiException {
        ContactAdd action = new ContactAdd()
            .setPhoneNumber("48327201200");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("contacts", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("phone_number", "48327201200");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddEmailAddressContactRequest() throws SmsapiException {
        ContactAdd action = new ContactAdd()
            .setEmail("john.doe@example.com");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("contacts", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("email", "john.doe@example.com");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddContactWithOptionalFieldsRequest() throws SmsapiException {
        ContactAdd action = new ContactAdd()
            .setPhoneNumber("48327201200")
            .setEmail("john.doe@example.com")
            .setFirstName("John")
            .setLastName("Doe")
            .setGender("male")
            .setBirthdayDate("2017-07-21")
            .setDescription("Resource description")
            .setCity("Example City")
            .setSource("Example Source");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("contacts", requestSpy.requestEndpoint);
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
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(ContactAdd action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
