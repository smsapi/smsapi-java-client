package pl.smsapi.api.action.user;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.action.BaseAction;
import pl.smsapi.api.response.PointsResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class UserGetPoints extends AbstractAction<PointsResponse> {

    public UserGetPoints() {
        setJson(true);
        params.put("credits", "1");
    }


    protected PointsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new PointsResponse(jsonObject.getDouble("points"));
    }

    @Override
    protected String endPoint() {
        return "user.do";
    }
}
