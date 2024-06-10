package pl.smsapi.api.action.sms;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SMSDeleteTest {

    @Test
    public void executeDeleteSmsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(DeletedStatusJsonMother.create());
        SMSDelete action = new SMSDelete("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("sms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("sch_del", "0f0f0f0f0f0f0f0f0f0f0f0f");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
