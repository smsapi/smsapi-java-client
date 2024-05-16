package pl.smsapi.api.action.contacts.groups;

public class GroupJsonMother {

    public static String create() {
        return
            "{" +
            "   \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "   \"name\": \"Example Group\"," +
            "   \"description\": \"Resource description\"," +
            "   \"contacts_count\": 0," +
            "   \"date_created\": \"2017-07-21T17:32:28Z\"," +
            "   \"date_updated\": \"2017-07-21T17:32:28Z\"," +
            "   \"created_by\": \"example_username\"," +
            "   \"idx\": \"example-user-provided-id-123\"," +
            "   \"contact_expire_after\": 0," +
            "}";
    }
}
