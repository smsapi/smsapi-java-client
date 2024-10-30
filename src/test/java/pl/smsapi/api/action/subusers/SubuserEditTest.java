package pl.smsapi.api.action.subusers;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserEditTest {

    @Test
    public void executeEditSubuserRequest() throws SmsapiException {
        SubuserEdit action = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(requestSpy.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserWithOptionalFieldsRequest() throws SmsapiException {
        SubuserEdit action = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f")
            .withPassword("NewStrongPassword123!")
            .withApiPassword("NewAnotherStrongPassword123!")
            .asActive()
            .withDescription("New resource description")
            .withPointsFromAccount(999.99)
            .withPointsPerMonth(111.11);

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[password]", "NewStrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "NewAnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "New resource description");
        expectedRequestPayload.put("points[from_account]", "999.99");
        expectedRequestPayload.put("points[per_month]", "111.11");
        assertEquals(requestSpy.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserAsActiveRequest() throws SmsapiException {
        SubuserEdit action = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f")
            .asActive();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("active", "1");
        assertEquals(requestSpy.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserAsInactiveRequest() throws SmsapiException {
        SubuserEdit action = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f")
            .asInactive();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("active", "0");
        assertEquals(requestSpy.requestPayload, expectedRequestPayload);
    }

    private ProxyRequestSpy executeAction(SubuserEdit action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
