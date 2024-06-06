package pl.smsapi.api.action.user;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.smsapi.api.UserFactory;
import pl.smsapi.api.response.PointsResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.TestSmsapi;

@Ignore
public class UserFactoryTest extends TestSmsapi {

    UserFactory apiFactory;

    @Before
    public void setUp() {
        super.setUp();
        apiFactory = new UserFactory(client, proxy);
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
