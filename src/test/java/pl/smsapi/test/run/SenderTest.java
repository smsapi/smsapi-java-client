package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SenderFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.SenderResponse;
import pl.smsapi.api.response.SendersResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

@Ignore
public class SenderTest extends TestSmsapi {
    private String senderTest = "test";

    SenderFactory apiFactory;

    @Before
    public void setUp() throws ClientException {
        apiFactory = new SenderFactory(getAuthorizationClient(), getProxy());
    }

    private void renderSenderItem(SenderResponse item) {
        if (item != null) {
            System.out.println("Sendername: " + item.getName()
                    + " Status: " + item.getStatus()
                    + " Default:" + item.isDefault());
        } else {
            System.out.println("Item is null");
        }
    }

    @Test
    public void senderAdd() throws SmsapiException {
        CountableResponse item = apiFactory.actionAdd(senderTest).execute();

        System.out.println("SenderAdd:" + item.getCount());
    }

    @Test
    public void senderDefault() throws SmsapiException {
        CountableResponse item = apiFactory.actionSetDefault("ECO").execute();

        System.out.println("SenderAdd:" + item.getCount());
    }

    @Test
    public void senderList() throws SmsapiException {
        SendersResponse result = apiFactory.actionList().execute();

        System.out.println("SenderList:");

        for (SenderResponse item : result.getList()) {
            renderSenderItem(item);
        }
    }

    @Test
    public void senderDelete() throws SmsapiException {
        CountableResponse item = apiFactory.actionDelete(senderTest).execute();

        System.out.println("SenderDelete:");

        if (item != null) {
            System.out.println("Sender: " + item.getCount());
        } else {
            System.out.println("Item is null");
        }
    }
}
