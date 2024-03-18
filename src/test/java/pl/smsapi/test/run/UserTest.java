package pl.smsapi.test.run;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.UserFactory;
import pl.smsapi.api.action.user.UserAdd;
import pl.smsapi.api.response.PointsResponse;
import pl.smsapi.api.response.UserResponse;
import pl.smsapi.api.response.UsersResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

import java.util.Random;

@Ignore
public class UserTest extends TestSmsapi {
    private String userTest = "junit";

    UserFactory apiFactory;

    @Before
    public void setUp() throws ClientException {
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
    public void userAddTest() throws SmsapiException {
        UserAdd action = apiFactory.actionAdd()
                .setUsername(userTest + new Random().nextLong())
                .setPassword(TestSmsapi.MD5Digest("100costma100"))
                .setPasswordApi(TestSmsapi.MD5Digest("200costam200"))
                .setActive(true)
                .setLimit(5.5)
                .setFullAccessPhoneBook(true);

        UserResponse item = action.execute();

        System.out.println("UserAdd:");

        renderUserItem(item);
    }

    @Test
    public void userListTest() throws SmsapiException {
        UsersResponse result = apiFactory.actionList().execute();

        System.out.println("UserList:");

        for (UserResponse item : result.getList()) {
            renderUserItem(item);
        }
    }

    @Test
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
