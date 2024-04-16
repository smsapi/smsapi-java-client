package pl.smsapi.api.response;

public class CountableResponse implements Response {

    protected int count;

    public CountableResponse(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
