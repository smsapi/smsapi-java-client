package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GroupDeleteTest {

    @Test
    public void executeDeleteGroupRequest() throws SmsapiException {
        GroupDelete action = new GroupDelete("0f0f0f0f0f0f0f0f0f0f0f0f");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("DELETE", requestSpy.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(GroupDelete action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy("");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
