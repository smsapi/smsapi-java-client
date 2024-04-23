package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.MmsFactory;
import pl.smsapi.api.action.mms.MMSDelete;
import pl.smsapi.api.action.mms.MMSGet;
import pl.smsapi.api.action.mms.MMSSend;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Date;

@Ignore
public class MmsTest extends TestSmsapi {
    private String numberTest = "694562829";
    private String[] ids;

    MmsFactory apiFactory;

    @Before
    public void setUp() {
        apiFactory = new MmsFactory(client, proxy);
    }

    @Test
    public void mmsSendTest() throws SmsapiException {
        final long time = (new Date().getTime() / 1000) + 86400;

        final String smil = "<smil><head><layout><root-layout height='100%' width='100%'/><region id='Image' width='100%' height='100%' left='0' top='0'/></layout></head><body><par><img src='http://www.smsapi.pl/assets/img/pages/errors/404.jpg' region='Image' /></par></body></smil>";

        MMSSend action = apiFactory.actionSend()
                .setSubject("Test")
                .setSmil(smil)
                .setTo(numberTest)
                .setDateSent(time);

        StatusResponse result = action.execute();

        System.out.println("MmsSend:");

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
    public void mmsGetTest() throws SmsapiException {
        System.out.println("MmsGet:");
        ids = readIds();

        if (ids != null) {

            MMSGet action = apiFactory.actionGet().ids(ids);
            StatusResponse result = action.execute();

            for (MessageResponse item : result.getList()) {
                renderMessageItem(item);
            }
        }
    }

    @Test
    public void mmsDeleteTest() throws SmsapiException {
        System.out.println("MmsDelete:");
        ids = readIds();

        if (ids != null) {

            MMSDelete action = apiFactory.actionDelete().ids(ids);

            CountableResponse item = action.execute();

            System.out.println("Delete: " + item.getCount());
        }
    }
}
