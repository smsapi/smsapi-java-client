package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.ContactResponse;

public class PhonebookContactGet extends AbstractAction<ContactResponse> {

    public PhonebookContactGet() {
        setJson(true);
    }

    /**
     * Set filter by contact phone number.
     */
    public PhonebookContactGet number(String number) {
        params.put("get_contact", number);
        return this;
    }

    protected ContactResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return
                new ContactResponse(
                        jsonObject.getString("number"),
                        jsonObject.optString("first_name"),
                        jsonObject.optString("last_name"),
                        jsonObject.optString("info"),
                        jsonObject.optString("birthday"),
                        jsonObject.optString("city"),
                        jsonObject.optString("gender"),
                        jsonObject.optInt("date_add"),
                        jsonObject.optInt("date_mod")
                );
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
