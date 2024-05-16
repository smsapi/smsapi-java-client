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
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        GroupEdit action = new GroupEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeEditGroupWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        GroupEdit action = new GroupEdit("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.setName("Example Group");
        action.setDescription("Resource description");
        action.setIdx("example-user-provided-id-123");

        action.execute();

        assertEquals("PUT", requestStub.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("idx", "example-user-provided-id-123");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
