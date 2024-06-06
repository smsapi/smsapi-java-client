package pl.smsapi.api.response;

public class CountableResponse implements Response {

    public final int count;

    public CountableResponse(int count) {
        this.count = count;
    }

    /**
     * @deprecated use {@link #count} instead
     */
    @Deprecated
    public int getCount() {
        return count;
    }
}
