package pl.smsapi.test.unit.action.subusers;

import org.junit.Test;
import pl.smsapi.api.action.subusers.SubuserDelete;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubuserDeleteTest {

    @Test
    public void executeDeleteSubuserRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SubuserResponseJsonMother.create());
        SubuserDelete action = new SubuserDelete("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("DELETE", requestStub.requestMethod);
        assertEquals("subusers/0f0f0f0f0f0f0f0f0f0f0f0f", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
