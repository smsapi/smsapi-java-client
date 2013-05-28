package pl.smsapi.test.run;

import java.io.File;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.test.SmsapiTest;

public class SmsTest extends SmsapiTest {

	private String numberTest = "694562829";
	private String[] ids;

	@Test
	//@Ignore
	public void smsSendTest() {

		SmsFactory smsApi = new SmsFactory(client());

		final long time = (new Date().getTime() / 1000) + 86400;

		StatusResponse result;
		BaseAction action = smsApi.actionSend()
				.setText("test message")
				.setTo(numberTest)
				.setDateSent(time);

		result = (StatusResponse) executeAction(action);

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

		SmsFactory smsApi = new SmsFactory(client());

		System.out.println("SmsGet:");
		ids = readIds();

		if (ids != null) {
			StatusResponse result;
			BaseAction action = smsApi.actionGet().ids(ids);

			result = (StatusResponse) executeAction(action);

			for (MessageResponse item : result.getList()) {
				renderMessageItem(item);
			}
		}
	}

	@Test
	//@Ignore
	public void smsDeleteTest() {

		SmsFactory smsApi = new SmsFactory(client());

		System.out.println("SmsDelete:");
		ids = readIds();

		if (ids != null) {
			CountableResponse item;
			BaseAction action = smsApi.actionDelete().ids(ids);;

			item = (CountableResponse) executeAction(action);

			System.out.println("Delete: " + item.getCount());
		}

		File file = new File(fileToIds);

		if (file.exists()) {
			file.delete();
		}
	}
}
