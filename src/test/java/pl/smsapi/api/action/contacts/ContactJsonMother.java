package pl.smsapi.api.action.contacts;

public class ContactJsonMother {

    public static String create() {
        return
            "{" +
            "  \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "  \"first_name\": \"John\"," +
            "  \"last_name\": \"Doe\"," +
            "  \"phone_number\": \"48327201200\"," +
            "  \"email\": \"john.doe@example.com\"," +
            "  \"gender\": \"undefined\"," +
            "  \"birthday_date\": \"2017-07-21\"," +
            "  \"description\": \"Resource description\"," +
            "  \"city\": \"Example City\"," +
            "  \"country\": \"Example Country\"," +
            "  \"source\": \"Example Source\"," +
            "  \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "  \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "  \"groups\": [" +
            "    {" +
            "       \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "       \"name\": \"Example Group\"," +
            "       \"description\": \"Resource description\"," +
            "       \"contacts_count\": 0," +
            "       \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "       \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "       \"created_by\": \"example_username\"," +
            "       \"idx\": \"example-user-provided-id-123\"," +
            "       \"contact_expire_after\": 0," +
            "    }" +
            "  ]" +
            " }";
    }
    
}
