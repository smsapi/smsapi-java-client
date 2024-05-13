package pl.smsapi.api.action.subusers;

public class SubusersJsonMother {

    public static String create() {
        return
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            SubuserJsonMother.create() +
            "  ]" +
            "}";
    }
}
