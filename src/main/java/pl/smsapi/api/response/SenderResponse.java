package pl.smsapi.api.response;

public class SenderResponse implements Response {

    private String name;
    private String status;
    private boolean primary;

    public SenderResponse(String name, String status, boolean primary) {
        this.name = name;
        this.status = status;
        this.primary = primary;
    }

    public boolean isDefault() {
        return this.primary;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
