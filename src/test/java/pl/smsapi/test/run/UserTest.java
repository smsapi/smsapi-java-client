package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.Client;
import pl.smsapi.api.UserFactory;
import pl.smsapi.api.action.user.UserAdd;
import pl.smsapi.api.response.PointsResponse;
import pl.smsapi.api.response.UserResponse;
import pl.smsapi.api.response.UsersResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.SmsapiTest;

public class UserTest extends SmsapiTest {

    private String userTest = "junit";

    UserFactory apiFactory;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        apiFactory = new UserFactory(getAuthorizationClient(), getProxy());
    }

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
    @Ignore
    public void userAddTest() throws SmsapiException {

        UserAdd action = apiFactory.actionAdd()
                .setUsername(userTest)
                .setPassword(Client.MD5Digest("100costma100"))
                .setPasswordApi(Client.MD5Digest("200costam200"))
                .setActive(true)
                .setLimit(5.5)
                .setFullAccessPhoneBook(true);

        UserResponse item = action.execute();

        System.out.println("UserAdd:");

        renderUserItem(item);
    }

    @Test
    @Ignore
    public void userListTest() throws SmsapiException {

        UsersResponse result = apiFactory.actionList().execute();

        System.out.println("UserList:");

        for (UserResponse item : result.getList()) {
            renderUserItem(item);
        }
    }

    @Test
    @Ignore
    public void userPointsTest() throws SmsapiException {

        PointsResponse item = apiFactory.actionGetPoints().execute();

        System.out.println("GetPoints:");

        if (item != null) {
            System.out.println("Points: " + item.getPoints());
        } else {
            System.out.println("Item is null");
        }
    }
}
