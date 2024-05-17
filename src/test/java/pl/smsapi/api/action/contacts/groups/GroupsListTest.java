package pl.smsapi.api.action.contacts.groups;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GroupsListTest {

    @Test
    public void executeGetGroupRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupsJsonMother.create());
        GroupsList action = new GroupsList();
        action.client(new ClientStub());
        action.proxy(requestStub);

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("contacts/groups", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(GroupsList action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(GroupsJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
