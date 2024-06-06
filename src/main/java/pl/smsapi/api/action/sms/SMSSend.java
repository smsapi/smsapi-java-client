package pl.smsapi.api.action.sms;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractSendAction;
import pl.smsapi.api.response.SendStatusResponse;

import java.util.Calendar;

public class SMSSend extends AbstractSendAction<SMSSend, SendStatusResponse> {

    /**
     * @deprecated use {@link SMSSend(String, String)} or {@link SMSSend(String[], String)} instead
     */
    @Deprecated
    public SMSSend() {
        setJson(true);
        params.put("encoding", "utf-8");
        params.put("details", "1");
    }

    public SMSSend(String to, String text) {
        setJson(true);
        params.put("encoding", "utf-8");
        params.put("details", "1");
        setTo(to);
        setText(text);
    }

    public SMSSend(String[] to, String text) {
        setJson(true);
        params.put("encoding", "utf-8");
        params.put("details", "1");
        setTo(to);
        setText(text);
    }

    @Override
    protected String endPoint() {
        return "sms.do";
    }

    /**
     * Set SMS text message.
     * <p/>
     * Content of one message is normally 160 characters per single SMS or 70 in case of using at least one special character
     *
     * @deprecated use {@link SMSSend(String, String)} or {@link SMSSend(String[], String)} instead
     */
    @Deprecated
    public SMSSend setText(String text) {
        params.put("message", text);
        return this;
    }

    /**
     * Set expiration date.
     * <p/>
     * Message expiration date (in unix timestamp) is a date after which message won't be delivered if it wasn't delivered yet.
     * The difference between date sent and expiration date can't be less than 1 hour and more than 12 hours.
     * Time will be set with tolerance +/- 5 minutes.
     */
    public SMSSend setDateExpire(String date) {
        params.put("expiration_date", date);
        return this;
    }

    /**
     * Set expiration date.
     * <p/>
     * Message expiration date (in unix timestamp) is a date after which message won't be delivered if it wasn't delivered yet.
     * The difference between date sent and expiration date can't be less than 1 hour and more than 12 hours.
     * Time will be set with tolerance +/- 5 minutes.
     */
    public SMSSend setDateExpire(long date) {
        Long time = date;
        return setDateExpire(time.toString());
    }

    /**
     * Set expiration date.
     * <p/>
     * Message expiration date (in unix timestamp) is a date after which message won't be delivered if it wasn't delivered yet.
     * The difference between date sent and expiration date can't be less than 1 hour and more than 12 hours.
     * Time will be set with tolerance +/- 5 minutes.
     */
    public SMSSend setDateExpire(Calendar cal) {
        long time = cal.getTimeInMillis() / 1000;
        return setDateExpire(time);
    }

    /**
     * Set name of the sender.
     * <p/>
     * Only verified names are being accepted.
     */
    public SMSSend setSender(String sender) {
        params.put("from", sender);
        return this;
    }

    /**
     * Set protection from send multipart messages.
     * <p/>
     * If the message will contain more than 160 chars (single message) it won't be sent and return error
     */
    public SMSSend setSingle(boolean single) {
        params.put("single", single ? "1" : "0");
        return this;
    }

    /**
     * Set protection from sending messages containing special characters.
     */
    public SMSSend setNoUnicode(boolean noUnicode) {
        params.put("nounicode", noUnicode ? "1" : "0");
        return this;
    }

    /**
     * Set SMS message data coding.
     * <p/>
     * This parameter allows to send WAP PUSH messages.
     */
    public SMSSend setDataCoding(String dataCoding) {
        params.put("datacoding", dataCoding);
        return this;
    }

    /**
     * Set SMS message in flash mode.
     * <p/>
     * Flash SMS are automatically presented on the mobile screen and have to be saved to be default stored in inbox.
     */
    public SMSSend setFlash(boolean flash) {
        params.put("flash", flash ? "1" : "0");
        return this;
    }

    /**
     * Set higher priority of sending message. Prohibited for bulk messages.
     */
    public SMSSend setFast(boolean fast) {
        params.put("fast", fast ? "1" : "0");
        return this;
    }

    /**
     * Set normalize SMS text.
     * <p/>
     * Removing dialectic characters from message.
     */
    public SMSSend setNormalize(boolean normalize) {
        params.put("normalize", normalize ? "1" : "0");
        return this;
    }

    /**
     * Set personalized parameters to bulk messages.
     */
    public SMSSend setParam(int i, String[] text) {
        return this.setParam(i, StringUtils.join(text, '|'));
    }

    /**
     * Set personalized parameters to bulk messages.
     */
    public SMSSend setParam(int i, String text) {

        if (i < 0 || i > 3) {
            throw new ArrayIndexOutOfBoundsException();
        }

        params.put("param" + Integer.toString(i + 1), text);

        return this;
    }
    
    /**
     * Set time restriction mode.
     */
    public SMSSend setTimeRestriction(String timeRestriction) {
        params.put("timerestriction", timeRestriction);
        return this;
    }

    public SMSSend setDiscountGroup(String $discountGroupName)
    {
        params.put("discount_group", $discountGroupName);
        return this;
    }

    protected SendStatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new SendStatusResponse(jsonObject.getInt("count"), jsonObject.getInt("parts"), jsonObject.optJSONArray("list"));
    }
}
