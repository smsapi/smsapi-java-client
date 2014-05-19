package pl.smsapi.api.action.phonebook;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class PhonebookContactDelete extends AbstractAction<RawResponse> {

    /**
     * Set contact phone number to delete.
     */
    public PhonebookContactDelete number(String number) {
        params.put("delete_contact", number);
        return this;
    }

    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
