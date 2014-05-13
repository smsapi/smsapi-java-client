package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.VmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.test.SmsapiTest;

import java.io.File;
import java.util.Date;

public class VmsTest extends SmsapiTest {

    private String numberTest = "694562829";
    private String[] ids;

    VmsFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new VmsFactory(getAuthorizationClient(), getProxy());
    }

    @Test
    //@Ignore
    public void vmsSendTtsTest() {

        final long time = (new Date().getTime() / 1000) + 86400;

        final String tts = "to jest test";

        StatusResponse result;
        BaseAction action = apiFactory.actionSend()
                .setTts(tts)
                .setTo(numberTest)
                .setInterval(300)
                .setDateSent(time);

        result = (StatusResponse) executeAction(action);

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
    //@Ignore
    public void vmsSendFileTest() {

        final long time = (new Date().getTime() / 1000) + 86400;

        final File fileAudio = new File("voice_small.wav");

        if (fileAudio.exists()) {

            StatusResponse result;
            BaseAction action = apiFactory.actionSend()
                    .setFile(fileAudio)
                    .setTo(numberTest)
                    .setDateSent(time);

            result = (StatusResponse) executeAction(action);

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
    }

    @Test
    //@Ignore
    public void vmsGetTest() {

        System.out.println("VmsGet:");
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
    public void vmsDeleteTest() {

        System.out.println("VmsDelete:");
        ids = readIds();

        if (ids != null) {
            CountableResponse item;
            BaseAction action = apiFactory.actionDelete().ids(ids);

            item = (CountableResponse) executeAction(action);

            System.out.println("Delete: " + item.getCount());
        }
    }
}