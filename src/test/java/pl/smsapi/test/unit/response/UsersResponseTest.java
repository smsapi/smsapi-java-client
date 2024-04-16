package pl.smsapi.test.unit.response;

import org.junit.Test;
import pl.smsapi.api.action.user.UserList;
import pl.smsapi.api.response.UserResponse;
import pl.smsapi.api.response.UsersResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyResponseStub;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class UsersResponseTest {

    @Test
    public void deserialize_empty_response() throws SmsapiException {
        UserList action = new UserList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "[]"
        ));

        UsersResponse response = action.execute();
        ArrayList<UserResponse> list = response.getList();

        assertTrue(list.isEmpty());
    }

    @Test
    public void deserialize_non_empty_response() throws SmsapiException {
        UserList action = new UserList();
        action.client(new ClientStub());
        action.proxy(new ProxyResponseStub(
            "[" +
                "{" +
                "    \"active\": \"1\"," +
                "    \"info\": null," +
                "    \"limit\": \"100.0000\"," +
                "    \"month_limit\": \"0.0000\"," +
                "    \"phonebook\": \"0\"," +
                "    \"senders\": \"0\"," +
                "    \"username\": \"user1\"" +
                "}," +
                "{" +
                "    \"active\": \"0\"," +
                "    \"info\": \"any\"," +
                "    \"limit\": \"200.0000\"," +
                "    \"month_limit\": \"20.0000\"," +
                "    \"phonebook\": \"1\"," +
                "    \"senders\": \"1\"," +
                "    \"username\": \"user2\"" +
                "}" +
            "]"
        ));

        UsersResponse response = action.execute();

        assertEquals(2, response.getList().size());

        Optional<UserResponse> user1 = response.getList().stream().filter(
            userResponse -> userResponse.getUsername().equals("user1")
        ).findFirst();
        assertTrue(user1.isPresent());
        assertEquals(100L, user1.get().getLimit(), 0.01);
        assertEquals(0L, user1.get().getMonthLimit(), 0.01);
        assertEquals(0, user1.get().getSenders());
        assertEquals(0, user1.get().getPhonebook());
        assertEquals(1, user1.get().getActive());
        assertEquals("", user1.get().getInfo());

        Optional<UserResponse> user2 = response.getList().stream().filter(
            userResponse -> userResponse.getUsername().equals("user2")
        ).findFirst();
        assertTrue(user2.isPresent());
        assertEquals(200L, user2.get().getLimit(),  0.01);
        assertEquals(20L, user2.get().getMonthLimit(),  0.01);
        assertEquals(1, user2.get().getSenders());
        assertEquals(1, user2.get().getPhonebook());
        assertEquals(0, user2.get().getActive());
        assertEquals("any", user2.get().getInfo());
    }
}
