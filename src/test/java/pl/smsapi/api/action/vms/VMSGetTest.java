package pl.smsapi.api.action.vms;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class VMSGetTest {

    @Test
    public void executeGetVmsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        VMSGet action = new VMSGet("0f0f0f0f0f0f0f0f0f0f0f0f");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("vms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("status", "0f0f0f0f0f0f0f0f0f0f0f0f");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeGetMultipleVmsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        VMSGet action = new VMSGet(new String[]{"0f0f0f0f0f0f0f0f0f0f0f0f", "0f0f0f0f0f0f0f0f0f0f0f01"});
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("vms.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("status", "0f0f0f0f0f0f0f0f0f0f0f0f|0f0f0f0f0f0f0f0f0f0f0f01");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
