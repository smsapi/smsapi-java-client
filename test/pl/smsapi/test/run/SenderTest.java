package pl.smsapi.test.run;

import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SenderFactory;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.SenderResponse;
import pl.smsapi.api.response.SendersResponse;
import pl.smsapi.test.SmsapiTest;

public class SenderTest extends SmsapiTest {

	private String senderTest = "Olek";

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

		SenderFactory smsApi = new SenderFactory(client());

		SenderResponse item;

		item = (SenderResponse) executeAction(smsApi.actionAdd(senderTest));

		System.out.println("SenderAdd:");

		renderSenderItem(item);
	}

	@Test
	//@Ignore
	public void senderList() {

		SenderFactory smsApi = new SenderFactory(client());

		SendersResponse result;

		result = (SendersResponse) executeAction(smsApi.actionList());

		System.out.println("SenderList:");

		for (SenderResponse item : result.getList()) {
			renderSenderItem(item);
		}
	}

	@Test
	//@Ignore
	public void senderDelete() {

		SenderFactory smsApi = new SenderFactory(client());

		CountableResponse item;

		item = (CountableResponse) executeAction(smsApi.actionDelete(senderTest));

		System.out.println("SenderDelete:");

		if (item != null) {
			System.out.println("Sender: " + item.getCount());
		} else {
			System.out.println("Item is null");
		}
	}
}
