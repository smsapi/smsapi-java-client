package pl.smsapi.test.unit.action.subusers;

public class SubuserResponseJsonMother {

    public static String create() {
        return
            "{" +
            "  \"id\": \"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "  \"username\": \"example_username\"," +
            "  \"active\": true," +
            "  \"description\": \"Resource description\"," +
            "  \"points\": {" +
            "    \"from_account\": 123.12," +
            "    \"per_month\": 321.21" +
            "  }" +
            "}";
    }
}
