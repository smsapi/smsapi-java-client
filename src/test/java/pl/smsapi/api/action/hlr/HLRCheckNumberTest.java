package pl.smsapi.api.action.hlr;

import org.junit.Test;
import pl.smsapi.api.action.sms.StatusJsonMother;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HLRCheckNumberTest {

    @Test
    public void executeCheckNumberRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        HLRCheckNumber action = new HLRCheckNumber("500600700");
        action.client(new ClientStub());
        action.proxy(requestStub);

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("hlrsync.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("number", "500600700");
        expectedRequestPayload.put("format", "json");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }

    @Test
    public void executeCheckNumberWithOptionalFieldsRequest() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        HLRCheckNumber action = new HLRCheckNumber("500600700");
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.setIDx("example-user-provided-id-123");

        action.execute();

        assertEquals("POST", requestStub.requestMethod);
        assertEquals("hlrsync.do", requestStub.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        expectedRequestPayload.put("number", "500600700");
        expectedRequestPayload.put("format", "json");
        expectedRequestPayload.put("idx", "example-user-provided-id-123");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
