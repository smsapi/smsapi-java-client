package pl.smsapi.api.action.sms.sendernames;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SendernameDeleteTest {

    @Test
    public void executeDeleteSendernameRequest() throws SmsapiException {
        SendernameDelete action = new SendernameDelete("java-client");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("DELETE", requestSpy.requestMethod);
        assertEquals("sms/sendernames/java-client", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(SendernameDelete action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy("");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
