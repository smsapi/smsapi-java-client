package pl.smsapi.api.action.hlr;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.response.CheckNumberResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import static org.junit.Assert.*;

@Ignore
public class HlrFactoryTest extends TestSmsapi {

    HlrFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new HlrFactory(client, proxy);
    }

    @Test
    public void checkNumberTest() throws SmsapiException {

        HLRCheckNumber actionCheckNumber = apiFactory.actionCheckNumber("500600700");

        CheckNumberResponse responseCheckNumber = actionCheckNumber.execute();

        assertNotNull(responseCheckNumber);
        assertEquals(1, responseCheckNumber.count);
        assertTrue(responseCheckNumber.list.stream().anyMatch(messageResponse -> messageResponse.getNumber().equals("500600700")));
    }
}
