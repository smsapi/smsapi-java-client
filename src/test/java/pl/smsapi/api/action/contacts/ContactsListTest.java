package pl.smsapi.api.action.contacts;

import org.junit.Test;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.test.doubles.ClientStub;
import pl.smsapi.test.doubles.ProxyRequestSpy;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ContactsListTest {

    @Test
    public void executeGetContactRequest() throws SmsapiException {
        ContactsList action = new ContactsList();

        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals("contacts", requestSpy.requestEndpoint);
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    @Test
    public void executeGetContactWithOptionalParametersRequest() throws SmsapiException {
        ContactsList action = new ContactsList()
            .filterByPhoneNumber("any_phone_number")
            .filterByEmail("any_email")
            .filterByFirstName("any_first_name")
            .filterByLastName("any_last_name")
            .filterByGroupId("any_group_id")
            .filterByGroupIds(Arrays.asList("first_group_id", "second_group_id").toArray(new String[0]))
            .filterByGender("any_gender")
            .filterByBirthdayDate("any_birthday_date")
            .orderBy("any_order")
            .limit(100)
            .offset(99);


        ProxyRequestSpy requestSpy = executeAction(action);

        assertEquals("GET", requestSpy.requestMethod);
        assertEquals(
            "contacts?" +
            "birthday_date=any_birthday_date&" +
            "email=any_email&" +
            "first_name=any_first_name&" +
            "gender=any_gender&" +
            "group_id=first_group_id%2Csecond_group_id&" +
            "last_name=any_last_name&" +
            "limit=100&" +
            "offset=99&" +
            "order_by=any_order&" +
            "phone_number=any_phone_number",
            requestSpy.requestEndpoint
        );
        HashMap<String, String> expectedRequestPayload = new HashMap<>();
        assertEquals(expectedRequestPayload, requestSpy.requestPayload);
    }

    private ProxyRequestSpy executeAction(ContactsList action) throws SmsapiException {
        ProxyRequestSpy requestStub = new ProxyRequestSpy(ContactsJsonMother.create());
        action.client(new ClientStub());
        action.proxy(requestStub);
        action.execute();
        return requestStub;
    }
}
