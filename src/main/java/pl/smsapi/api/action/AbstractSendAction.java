package pl.smsapi.api.action;

import pl.smsapi.StringUtils;
import pl.smsapi.api.response.Response;

import java.util.Calendar;

public abstract class AbstractSendAction<T, TResponse extends Response> extends AbstractAction<TResponse> {

    /**
     * Set mobile phone number of the recipients.
     */
    public T setTo(String to) {
        params.put("to", to);
        return (T) this;
    }

    /**
     * Set mobile phone number of the recipients.
     */
    public T setTo(String[] to) {
        return setTo(StringUtils.join(to, ','));
    }

    /**
     * Message will not be charged and send if set true (default: false)
     */
    public T setTest(boolean flag) {
        params.put("test", flag ? "1" : "0");
        return (T) this;
    }

    /**
     * Set name of the group from the phone book to which message should be sent.
     */
    public T setGroup(String group) {
        params.put("group", group);
        return (T) this;
    }

    /**
     * Set scheduled date sending message.
     * <p/>
     * Setting a past date will result in sending message instantly.
     */
    public T setDateSent(String date) {
        params.put("date", date);
        return (T) this;
    }

    /**
     * Set scheduled date sending message.
     * <p/>
     * Setting a past date will result in sending message instantly.
     */
    public T setDateSent(long date) {
        Long time = date;
        return setDateSent(time.toString());
    }

    /**
     * Set scheduled date sending message.
     * <p/>
     * Setting a past date will result in sending message instantly.
     */
    public T setDateSent(Calendar cal) {
        long time = cal.getTimeInMillis() / 1000;
        return setDateSent(time);
    }

    /**
     * Set optional custom value sent with SMS and sent back in CALLBACK.
     */
    public T setIDx(String idx) {
        params.put("idx", idx);
        return (T) this;
    }

    /**
     * Set optional custom value sent with SMS and sent back in CALLBACK.
     */
    public T setIDx(String[] idx) {
        return setIDx(StringUtils.join(idx, '|'));
    }

    /**
     * Set checking idx is unique.
     * <p/>
     * Prevents from sending more than one message with the same idx.
     * When this parameter is set and message with the same idx was already sent error 53 is returned.
     */
    public T setCheckIDx(boolean check) {
        params.put("check_idx", check ? "1" : "0");
        return (T) this;
    }

    /**
     * Set affiliate code.
     */
    public T setPartner(String partner) {
        params.put("partner_id", partner);
        return (T) this;
    }

    public T setNotifyUrl(String url) {
        params.put("notify_url", url);
        return (T) this;
    }
}
