package pl.smsapi.api.action.subusers;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;

public class SubusersList extends AbstractAction<Subusers> {
    @Override
    protected String endPoint() {
        return "subusers";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected Subusers createResponse(String data) {
        return new Subusers.SubusersFromJsonFactory().createFrom(new JSONObject(data));
    }
}
