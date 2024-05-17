package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GroupAddTest {

    @Test
    public void executeAddGroupRequest() throws SmsapiException {
        GroupAdd action = new GroupAdd("Example Group");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("contacts/groups", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeAddGroupWithOptionalFieldsRequest() throws SmsapiException {
        GroupAdd action = new GroupAdd("Example Group")
            .setDescription("Resource description")
            .setIdx("example-user-provided-id-123");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("contacts/groups", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("idx", "example-user-provided-id-123");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(GroupAdd action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
