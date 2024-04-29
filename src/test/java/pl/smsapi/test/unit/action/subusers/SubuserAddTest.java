package pl.smsapi.test.unit.action.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubuserAdd;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserAddTest {

    @Test
    public void executeAddSubuserRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("format", "json");
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddSubuserRequestWithOptionalFields() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.setApiPassword("AnotherStrongPassword123!");
        action.setAsActive();
        action.setDescription("Resource description");
        action.setPointsFromAccount(11.11);
        action.setPointsPerMonth(22.22);
        action.setEmail("smsapi-java-client@example.com");

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("format", "json");
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "AnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("points[from_account]", "11.11");
        expectedRequestPayload.put("points[per_month]", "22.22");
        expectedRequestPayload.put("email", "smsapi-java-client@example.com");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
