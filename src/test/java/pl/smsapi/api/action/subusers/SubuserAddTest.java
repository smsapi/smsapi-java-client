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
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("subusers", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddSubuserWithOptionalFieldsRequest() throws SmsapiException {
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!")
            .withApiPassword("AnotherStrongPassword123!")
            .asActive()
            .withDescription("Resource description")
            .withPointsFromAccount(11.11)
            .withPointsPerMonth(22.22);

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("subusers", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "AnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("points[from_account]", "11.11");
        expectedRequestPayload.put("points[per_month]", "22.22");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddSubuserAsActiveRequest() throws SmsapiException {
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!")
            .asActive();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("subusers", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("active", "1");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddSubuserAsInactiveRequest() throws SmsapiException {
        SubuserAdd action = new SubuserAdd("smsapi-java-client", "StrongPassword123!")
            .asInactive();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("subusers", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[username]", "smsapi-java-client");
        expectedRequestPayload.put("credentials[password]", "StrongPassword123!");
        expectedRequestPayload.put("active", "0");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(SubuserAdd action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
