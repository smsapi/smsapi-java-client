package pl.smsapi.api.action.vms;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class VMSSendTest {

    @Test
    public void executeSendVmsFromTTSRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        VMSSend action = new VMSSend("48123123123", "text to speech");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("vms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("tts", "text to speech");
        expectedRequestPayload.put("to", "48123123123");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeSendMultipleVmsFromTTSRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        VMSSend action = new VMSSend(new String[]{"48123123123", "48123123124"}, "text to speech");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("vms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("tts", "text to speech");
        expectedRequestPayload.put("to", "48123123123,48123123124");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
