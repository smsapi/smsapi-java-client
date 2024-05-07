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
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);
        actionEdit.withPassword("NewStrongPassword123!");
        actionEdit.withApiPassword("NewAnotherStrongPassword123!");
        actionEdit.asActive();
        actionEdit.withDescription("New resource description");
        actionEdit.withPointsFromAccount(999.99);
        actionEdit.withPointsPerMonth(111.11);

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("credentials[password]", "NewStrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "NewAnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "New resource description");
        expectedRequestPayload.put("points[from_account]", "999.99");
        expectedRequestPayload.put("points[per_month]", "111.11");
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserAsActiveRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);
        actionEdit.asActive();

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("active", "1");
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserAsInactiveRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);
        actionEdit.asInactive();

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("active", "0");
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }
}
