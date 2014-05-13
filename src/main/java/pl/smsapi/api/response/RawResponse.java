package pl.smsapi.api.response;

public class RawResponse implements Response {

	private String text;

	public RawResponse(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
