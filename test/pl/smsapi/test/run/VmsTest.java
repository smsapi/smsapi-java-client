package pl.smsapi.test.run;

import java.io.File;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.VmsFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.CountableResponse;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ActionException;
import pl.smsapi.test.SmsapiTest;

public class VmsTest extends SmsapiTest {

	private String numberTest = "694562829";
	private String[] ids;

	@Test
	//@Ignore
	public void vmsSendTtsTest() {

		VmsFactory smsApi = new VmsFactory(client());

		final long time = (new Date().getTime() / 1000) + 86400;

		final String tts = "to jest test";

		StatusResponse result;
		BaseAction action = smsApi.actionSend()
				.setTts(tts)
				.setTo(numberTest)
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
	@Ignore
	public void vmsSendFileTest() {

		VmsFactory smsApi = new VmsFactory(client());

		final long time = (new Date().getTime() / 1000) + 86400;

		final File fileAudio = new File("voice_small.wav");

		if (fileAudio.exists()) {

			StatusResponse result;
			BaseAction action = smsApi.actionSend()
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

		VmsFactory smsApi = new VmsFactory(client());

		System.out.println("VmsGet:");
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
	public void vmsDeleteTest() {

		VmsFactory smsApi = new VmsFactory(client());

		System.out.println("VmsDelete:");
		ids = readIds();

		if (ids != null) {
			CountableResponse item;
			BaseAction action = smsApi.actionDelete().ids(ids);;

			item = (CountableResponse) executeAction(action);

			System.out.println("Delete: " + item.getCount());
		}
	}
}
