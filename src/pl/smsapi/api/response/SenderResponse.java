package pl.smsapi.api.response;

public class SenderResponse implements Response {

	private String name;
	private String status;
	private String setDefault;

	public SenderResponse(String name, String status, String major) {
		this.name = name;
		this.status = status;
		this.setDefault = major;
	}

	public boolean isDefault() {
		return (setDefault != null && setDefault.equals("default"));
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}
}
