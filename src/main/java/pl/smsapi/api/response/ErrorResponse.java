package pl.smsapi.api.response;

public class ErrorResponse implements Response {

    public int code = 0;
    public String message = "";

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public boolean isError() {
        return (code != 0);
    }
}
