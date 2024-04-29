package pl.smsapi.test.unit.action.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubusersList;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubusersListTest {

    @Test
    public void executeListSubusersRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubusersResponseJsonMother.create());
        SubusersList action = new SubusersList();
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("GET", requestStub.requestMethod);
        assertEquals("subusers", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
