package pl.smsapi.test.unit.action.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubuserGet;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserGetTest {

    @Test
    public void executeGetSubuserRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserGet action = new SubuserGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("GET", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
