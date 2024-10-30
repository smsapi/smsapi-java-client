package pl.smsapi.api.action.contacts.groups;

public class GroupsJsonMother {

    public static String create() {
        return
            "{" +
            "  \"size\": 1," +
            "  \"collection\": [" +
            GroupJsonMother.create() +
            "  ]" +
            "}";
    }
}
