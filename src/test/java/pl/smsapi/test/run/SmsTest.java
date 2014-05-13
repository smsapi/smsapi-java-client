package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.action.sms.Send;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.SmsapiTest;

import java.io.File;
import java.util.Date;

public class SmsTest extends SmsapiTest {

    private String numberTest = "694562829";
    private String[] ids;

    SmsFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new SmsFactory(getAuthorizationClient(), getProxy());
    }

    @Test
    //@Ignore
    public void smsSendTest() throws SmsapiException {

        final long time = (new Date().getTime() / 1000) + 86400;

        Send action = apiFactory.actionSend()
                .setText("test message")
                .setTo(numberTest)
                .setDateSent(time);

        StatusResponse result = action.execute();

        System.out.println("SmsSend:");

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
    //@Ignore
    public void smsGetTest() {

        System.out.println("SmsGet:");
        ids = readIds();

        if (ids != null) {
            StatusResponse result;
            BaseAction action = apiFactory.actionGet().ids(ids);

            result = (StatusResponse) executeAction(action);

            for (MessageResponse item : result.getList()) {
                renderMessageItem(item);
            }
        }
    }

    @Test
    //@Ignore
    public void smsDeleteTest() {

        System.out.println("SmsDelete:");
        ids = readIds();

        if (ids != null) {
            CountableResponse item;
            BaseAction action = apiFactory.actionDelete().ids(ids);
            ;

            item = (CountableResponse) executeAction(action);

            System.out.println("Delete: " + item.getCount());
        }

        File file = new File(fileToIds);

        if (file.exists()) {
            file.delete();
        }
    }
}
