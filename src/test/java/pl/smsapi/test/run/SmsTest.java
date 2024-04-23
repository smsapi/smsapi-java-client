package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSDelete;
import pl.smsapi.api.action.sms.SMSGet;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.SendStatusResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.io.File;
import java.util.Date;

@Ignore
public class SmsTest extends TestSmsapi {

    SmsFactory apiFactory;
    private String numberTest = "694562829";
    private String[] ids;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new SmsFactory(client, proxy);
    }

    @Test
    public void smsSendTest() throws SmsapiException {
        final long time = (new Date().getTime() / 1000) + 86400;

        SMSSend action = apiFactory.actionSend()
                .setText("test message")
                .setTo(numberTest)
                .setDateSent(time)
                .setNotifyUrl("http://example.com/back");

        SendStatusResponse result = action.execute();

        System.out.println("SmsSend:");
        System.out.println("  parts:" + result.getParts());

        if (result.getCount() > 0) {
            ids = new String[result.getCount()];
        }

        int i = 0;

        for (MessageResponse item : result.getList()) {
            if (!item.isError()) {
                renderMessageItem(item);
                ids[i] = item.getId();
                i++;
            }
        }

        if (ids.length > 0) {
            writeIds(ids);
        }
    }

    @Test
    public void smsGetTest() throws SmsapiException {
        System.out.println("SmsGet:");
        ids = readIds();

        if (ids != null) {

            SMSGet action = apiFactory.actionGet().ids(ids);

            StatusResponse result = action.execute();

            for (MessageResponse item : result.getList()) {
                renderMessageItem(item);
            }
        }
    }

    @Test
    public void smsDeleteTest() throws SmsapiException {
        System.out.println("SmsDelete:");
        ids = readIds();

        if (ids != null) {
            SMSDelete action = apiFactory.actionDelete().id(ids[0]);

            CountableResponse item = action.execute();

            System.out.println("Delete: " + item.getCount());
        }

        File file = new File(fileToIds);

        if (file.exists()) {
            file.delete();
        }
    }
}
