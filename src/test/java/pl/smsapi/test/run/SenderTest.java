package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Test;
import pl.smsapi.api.PhonebookFactory;
import pl.smsapi.api.SenderFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.SenderResponse;
import pl.smsapi.api.response.SendersResponse;
import pl.smsapi.test.SmsapiTest;

public class SenderTest extends SmsapiTest {

	private String senderTest = "test";

    SenderFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

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
	//@Ignore
	public void senderAdd() {

        CountableResponse item;

		item = (CountableResponse) executeAction(apiFactory.actionAdd(senderTest));

		System.out.println("SenderAdd:" + item.getCount());
	}

	@Test
	//@Ignore
	public void senderList() {

		SendersResponse result;

		result = (SendersResponse) executeAction(apiFactory.actionList());

		System.out.println("SenderList:");

		for (SenderResponse item : result.getList()) {
			renderSenderItem(item);
		}
	}

	@Test
	//@Ignore
	public void senderDelete() {

		CountableResponse item;

		item = (CountableResponse) executeAction(apiFactory.actionDelete(senderTest));

		System.out.println("SenderDelete:");

		if (item != null) {
			System.out.println("Sender: " + item.getCount());
		} else {
			System.out.println("Item is null");
		}
	}
}
