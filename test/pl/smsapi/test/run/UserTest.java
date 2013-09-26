package pl.smsapi.test.run;

import org.junit.Test;
import pl.smsapi.Client;
import pl.smsapi.api.UserFactory;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.PointsResponse;
import pl.smsapi.api.response.UserResponse;
import pl.smsapi.api.response.UsersResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.test.SmsapiTest;

public class UserTest extends SmsapiTest {

	private String userTest = "junit";

	private void renderUserItem(UserResponse item) {

		if (item != null) {
			System.out.println("Username: " + item.getUsername()
					+ " Limit: " + item.getLimit()
					+ " MouthLimit:" + item.getMonthLimit()
					+ " Phonebook:" + item.getPhonebook()
					+ " Senders: " + item.getSenders()
					+ " Active: " + item.getActive());
		} else {
			System.out.println("Item is null");
		}
	}

	@Test
	//@Ignore
	public void userAddTest() throws ClientException {

		UserFactory smsApi = new UserFactory(client());

		UserResponse item;
		BaseAction action = smsApi.actionAdd()
				.setUsername(userTest)
				.setPassword(Client.MD5Digest("100costma100"))
				.setPasswordApi(Client.MD5Digest("200costam200"))
				.setActive(true)
				.setLimit(5.5)
				.setPhonebook(true);

		item = (UserResponse) executeAction(action);

		System.out.println("UserAdd:");

		renderUserItem(item);
	}

	@Test
	//@Ignore
	public void userListTest() {

		UserFactory smsApi = new UserFactory(client());

		UsersResponse result;

		result = (UsersResponse) executeAction(smsApi.actionList());

		System.out.println("UserList:");

		for (UserResponse item : result.getList()) {
			renderUserItem(item);
		}
	}

	@Test
	//@Ignore
	public void userPointsTest() {

		UserFactory smsApi = new UserFactory(client());

		PointsResponse item;

		item = (PointsResponse) executeAction(smsApi.actionGetPoints());

		System.out.println("GetPoints:");

		if (item != null) {
			System.out.println("Points: " + item.getPoints());
		} else {
			System.out.println("Item is null");
		}
	}
}
