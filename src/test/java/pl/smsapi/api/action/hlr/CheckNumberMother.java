package pl.smsapi.api.action.hlr;

public class CheckNumberMother {

    public static String create() {
        return
            "{" +
            "    \"count\": 1," +
            "    \"list\": [" +
            "        {" +
            "            \"date\": 1712565008," +
            "            \"id\": \"1\"," +
            "            \"info\": \"Resource description\"," +
            "            \"mcc\": 260," +
            "            \"mnc\": 3," +
            "            \"number\": \"500600700\"," +
            "            \"ported\": 0," +
            "            \"ported_from\": 3," +
            "            \"price\": 0.04," +
            "            \"status\": \"OK\"" +
            "        }" +
            "    ]" +
            "}";
    }
}
