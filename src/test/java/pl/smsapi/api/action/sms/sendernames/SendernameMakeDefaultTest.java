package pl.smsapi.api.action.sms.sendernames;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SendernameMakeDefaultTest {

    @Test
    public void executeMakeDefaultSendernameRequest() throws SmsapiException {
        SendernameMakeDefault action = new SendernameMakeDefault("java-client");

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("POST", requestSpy.requestMethod);
        assertEquals("sms/sendernames/java-client/commands/make_default", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(SendernameMakeDefault action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy("");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
