package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GroupGetTest {

    @Test
    public void executeGetGroupRequest() throws SmsapiException {
        GroupGet action = new GroupGet("0f0f0f0f0f0f0f0f0f0f0f0f");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("contacts/groups/0f0f0f0f0f0f0f0f0f0f0f0f", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(GroupGet action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
