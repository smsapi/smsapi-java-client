package pl.smsapi.test.unit.action.subusers;

public class SubusersResponseJsonMother {

    public static String create() {
        return
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            SubuserResponseJsonMother.create() +
            "  ]" +
            "}";
    }
}
