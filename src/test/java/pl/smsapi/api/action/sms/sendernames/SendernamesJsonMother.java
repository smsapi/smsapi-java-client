package pl.smsapi.api.action.sms.sendernames;

public class SendernamesJsonMother {

    public static String create() {
        return
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            SendernameJsonMother.create() +
            "  ]" +
            "}";
    }
}
