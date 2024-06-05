package pl.smsapi.api.action.sms;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SMSSendTest {

    @Test
    public void executeSendSms() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        SMSSend action = new SMSSend("48123123123", "test message");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("sms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("message", "test message");
        expectedRequestPayload.put("to", "48123123123");
        expectedRequestPayload.put("format", "json");
        expectedRequestPayload.put("details", "1");
        expectedRequestPayload.put("encoding", "utf-8");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
