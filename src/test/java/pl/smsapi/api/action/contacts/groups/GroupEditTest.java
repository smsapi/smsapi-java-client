package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GroupEditTest {

    @Test
    public void executeEditGroupRequest() throws SmsapiException {
        GroupEdit action = new GroupEdit("0f0f0f0f0f0f0f0f0f0f0f0f");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeEditGroupWithOptionalFieldsRequest() throws SmsapiException {
        GroupEdit action = new GroupEdit("0f0f0f0f0f0f0f0f0f0f0f0f")
            .withName("Example Group")
            .withDescription("Resource description")
            .withIdx("example-user-provided-id-123");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("PUT", requestSpy.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("idx", "example-user-provided-id-123");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(GroupEdit action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
