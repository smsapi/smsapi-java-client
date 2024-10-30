package pl.smsapi.api.action.hlr;

import org.junit.Test;
import pl.smsapi.api.response.CheckNumberResponse;
import pl.smsapi.api.response.NumberResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class CheckNumberResponseTest {

    @Test
    public void deserialize_non_empty_response() throws SmsapiException {
        HLRCheckNumber action = new HLRCheckNumber("500600700");
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(CheckNumberMother.create()));

        CheckNumberResponse response = action.execute();

        assertFalse(response.list.isEmpty());
        assertEquals(1, response.count);

        Optional<NumberResponse> hlr1 = response.list.stream().filter(
                hlrResponse -> hlrResponse.getId().equals("1")
        ).findFirst();
        assertTrue(hlr1.isPresent());
        assertEquals(1712565008, hlr1.get().getDate());
        assertEquals("Resource description", hlr1.get().getInfo());
        assertEquals(260, hlr1.get().getMcc());
        assertEquals(3, hlr1.get().getMnc());
        assertEquals("500600700", hlr1.get().getNumber());
        assertEquals(0, hlr1.get().getPorted());
        assertEquals(3, hlr1.get().getPortedFrom());
        assertEquals(0.04, hlr1.get().getPoints(), 0.01);
        assertEquals("OK", hlr1.get().getStatus());
    }
}
