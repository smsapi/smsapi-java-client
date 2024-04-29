package pl.smsapi.test.unit.action.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubuserEdit;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserEditTest {

    @Test
    public void executeEditSubuserRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("format", "json");
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }

    @Test
    public void executeEditSubuserRequestWithOptionalFields() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserEdit actionEdit = new SubuserEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        actionEdit.client(new ClientStub());
        actionEdit.proxy(requestStub);
        actionEdit.setPassword("NewStrongPassword123!");
        actionEdit.setApiPassword("NewAnotherStrongPassword123!");
        actionEdit.setAsActive();
        actionEdit.setDescription("New resource description");
        actionEdit.setPointsFromAccount(999.99);
        actionEdit.setPointsPerMonth(111.11);

        actionEdit.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("format", "json");
        expectedRequestPayload.put("credentials[password]", "NewStrongPassword123!");
        expectedRequestPayload.put("credentials[api_password]", "NewAnotherStrongPassword123!");
        expectedRequestPayload.put("active", "1");
        expectedRequestPayload.put("description", "New resource description");
        expectedRequestPayload.put("points[from_account]", "999.99");
        expectedRequestPayload.put("points[per_month]", "111.11");
        assertEquals(requestStub.requestPayload, expectedRequestPayload);
    }
}
