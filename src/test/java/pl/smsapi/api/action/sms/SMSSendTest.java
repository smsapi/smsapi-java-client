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

    @Test
    public void executeSendSmsWithOptionalFields() throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(StatusJsonMother.create());
        SMSSend action = new SMSSend("48123123123", "test message")
            .setDateExpire("1717500698")
            .setSender("test")
            .setSingle(true)
            .setNoUnicode(true)
            .setDataCoding("any")
            .setFlash(true)
            .setNormalize(true)
            .setParam(1, "param")
            .setParam(2, new String[]{"param1", "param2"})
            .setDiscountGroup("group1");
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
        expectedRequestPayload.put("expiration_date", "1717500698");
        expectedRequestPayload.put("datacoding", "any");
        expectedRequestPayload.put("param3", "param1|param2");
        expectedRequestPayload.put("discount_group", "group1");
        expectedRequestPayload.put("param2", "param");
        expectedRequestPayload.put("single", "1");
        expectedRequestPayload.put("nounicode", "1");
        expectedRequestPayload.put("normalize", "1");
        expectedRequestPayload.put("flash", "1");
        expectedRequestPayload.put("from", "test");
        assertEquals(expectedRequestPayload, requestStub.requestPayload);
    }
}
