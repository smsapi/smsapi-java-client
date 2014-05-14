package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.action.hlr.HLRCheckNumber;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;
import pl.smsapi.test.SmsapiTest;

import java.io.File;
import java.util.Date;

public class HlrTest extends SmsapiTest {

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
