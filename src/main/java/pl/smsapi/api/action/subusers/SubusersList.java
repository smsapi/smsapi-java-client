package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.subusers.SubusersResponse;

public class SubusersList extends AbstractAction<SubusersResponse> {
    @Override
    protected String endPoint() {
        return "subusers";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected SubusersResponse createResponse(String data) {
        return new SubusersResponse.SubusersFromJsonFactory().createFrom(new JSONObject(data));
    }
}
