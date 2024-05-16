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
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        GroupAdd action = new GroupAdd("Example Group");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("contacts/groups", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeAddGroupWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        GroupAdd action = new GroupAdd("Example Group");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.setDescription("Resource description");
        action.setIdx("example-user-provided-id-123");

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("contacts/groups", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("name", "Example Group");
        expectedRequestPayload.put("description", "Resource description");
        expectedRequestPayload.put("idx", "example-user-provided-id-123");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
