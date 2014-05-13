package pl.smsapi.test.run;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.MmsFactory;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.test.SmsapiTest;

public class MmsTest extends SmsapiTest {

	private String numberTest = "694562829";
	private String[] ids;

    MmsFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new MmsFactory(getAuthorizationClient(), getProxy());
    }

	@Test
	//@Ignore
	public void mmsSendTest() {

		final long time = (new Date().getTime() / 1000) + 86400;

		final String smil = "<smil><head><layout><root-layout height='100%' width='100%'/><region id='Image' width='100%' height='100%' left='0' top='0'/></layout></head><body><par><img src='http://www.smsapi.pl/media/mms.jpg' region='Image' /></par></body></smil>";

		StatusResponse result;
		BaseAction action = apiFactory.actionSend()
				.setSubject("Test")
				.setSmil(smil)
				.setTo(numberTest)
				.setDateSent(time);

		result = (StatusResponse) executeAction(action);

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
	//@Ignore
	public void mmsGetTest() {

		System.out.println("MmsGet:");
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
	public void mmsDeleteTest() {

		System.out.println("MmsDelete:");
		ids = readIds();

		if (ids != null) {
			CountableResponse item;
			BaseAction action = apiFactory.actionDelete().ids(ids);;

			item = (CountableResponse) executeAction(action);

			System.out.println("Delete: " + item.getCount());
		}
	}
}
