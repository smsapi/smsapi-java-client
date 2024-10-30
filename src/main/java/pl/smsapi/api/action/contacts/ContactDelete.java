package pl.smsapi.api.action.contacts;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class ContactDelete extends AbstractAction<RawResponse> {

    private final String id;

    public ContactDelete(String id) {
        this.id = id;
    }

    @Override
    protected String endPoint() {
        return "contacts/" + id;
    }

    @Override
    protected String httpMethod() {
        return "DELETE";
    }

    @Override
    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }
}
