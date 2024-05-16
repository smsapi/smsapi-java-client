package pl.smsapi.api.action.contacts;

public class ContactsJsonMother {

    public static String create() {
        return
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            ContactJsonMother.create() +
            "  ]" +
            "}";
    }
}
