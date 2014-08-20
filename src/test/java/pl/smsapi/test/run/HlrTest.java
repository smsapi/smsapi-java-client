package pl.smsapi.test.run;

import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.action.hlr.HLRCheckNumber;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;
import pl.smsapi.test.TestSmsapi;

public class HlrTest extends TestSmsapi {

    @Test
    @Ignore
    public void checkNumberTest() throws SmsapiException {

        HLRCheckNumber action = new HLRCheckNumber();
        action.client(getAuthorizationClient());
        action.proxy(new ProxyNative("http://smsapi_panel.lpajak.devsms.com/"));

        action.setNumber("500600700");

        action.execute();
    }
}
