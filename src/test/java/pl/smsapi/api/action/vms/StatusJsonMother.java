package pl.smsapi.api.action.vms;

public class StatusJsonMother {

    public static String create() {
        return
            "{" +
            "   \"count\":1," +
            "   \"list\":[" +
            "       {" +
            "           \"id\":\"0f0f0f0f0f0f0f0f0f0f0f0f\"," +
            "           \"points\":0.21," +
            "           \"number\":\"48123123123\"," +
            "           \"date_sent\":1717500698," +
            "           \"submitted_number\":\"123123123\"," +
            "           \"status\":\"QUEUE\"," +
            "           \"error\":null," +
            "           \"idx\":null," +
            "           \"parts\":1" +
            "       }" +
            "   ]" +
            "}";
    }
}
