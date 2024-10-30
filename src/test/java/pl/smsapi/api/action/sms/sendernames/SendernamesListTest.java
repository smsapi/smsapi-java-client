package pl.smsapi.api.action.sms.sendernames;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SendernamesListTest {

    @Test
    public void executeListSendernamesRequest() throws SmsapiException {
        SendernamesList action = new SendernamesList();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("sms/sendernames", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(SendernamesList action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(SendernamesJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
