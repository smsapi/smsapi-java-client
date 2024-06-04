package pl.smsapi.api.action.vms;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.VmsFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

@Ignore
public class VmsFactoryTest extends TestSmsapi {
    private String numberTest = "694562829";
    private String[] ids;

    VmsFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new VmsFactory(client, proxy);
    }

    @Test
    public void vmsSendTtsTest() throws SmsapiException {
        final long time = (new Date().getTime() / 1000) + 86400;

        final String tts = "to jest test";

        VMSSend action = apiFactory.actionSend()
                .setTts(tts)
                .setTo(numberTest)
                .setInterval(300)
                .setDateSent(time);

        StatusResponse result = action.execute();

        System.out.println("VmsSend:");

        ids = new String[result.getCount()];
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
    public void vmsSendFileTest() throws FileNotFoundException, SmsapiException {
        final long time = (new Date().getTime() / 1000) + 86400;

        final File fileAudio = new File("src/test/java/pl/smsapi/test/voice_small.wav");

        VMSSend action = apiFactory.actionSend()
                .setFile(fileAudio)
                .setTo(numberTest)
                .setDateSent(time);

        StatusResponse result = action.execute();

        System.out.println("VmsSend:");

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
    public void vmsGetTest() throws SmsapiException {
        System.out.println("VmsGet:");
        ids = readIds();

        if (ids != null) {
            VMSGet action = apiFactory.actionGet().ids(ids);

            StatusResponse result = action.execute();

            for (MessageResponse item : result.getList()) {
                renderMessageItem(item);
            }
        }
    }

    @Test
    public void vmsDeleteTest() throws SmsapiException {
        System.out.println("VmsDelete:");
        ids = readIds();

        if (ids != null) {
            VMSDelete action = apiFactory.actionDelete().ids(ids);

            CountableResponse item = action.execute();

            System.out.println("Delete: " + item.getCount());
        }
    }
}