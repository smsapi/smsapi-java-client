package pl.smsapi.api.action.subusers;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserAddTest {

    @Test
    public void executeAddSubuserRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddSubuserWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.withApiPassword("AnotherStrongPassword123!");
        action.asActive();
        action.withDescription("Resource description");
        action.withPointsFromAccount(11.11);
        action.withPointsPerMonth(22.22);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "AnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("points[from_account]", "11.11");
        expectedRequestPayload.put("points[per_month]", "22.22");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddSubuserAsActiveRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.asActive();

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("active", "1");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddSubuserAsInactiveRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.asInactive();

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("active", "0");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
