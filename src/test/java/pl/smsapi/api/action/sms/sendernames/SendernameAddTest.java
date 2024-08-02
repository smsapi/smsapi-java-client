package pl.smsapi.api.action.sms.sendernames;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SendernameAddTest {

    @Test
    public void executeAddSendernameRequest() throws SmsapiException {
        SendernameAdd action = new SendernameAdd("java-client");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("sms/sendernames", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("sender", "java-client");
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(SendernameAdd action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SendernameJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
