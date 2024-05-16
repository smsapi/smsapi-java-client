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

        action.execute();

        assertEquals("GET", requestStub.requestMethod);
        assertEquals("contacts/groups", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
