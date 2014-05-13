package pl.smsapi.api.response;

public class MessageResponse implements Response {

	private String id;
	private double points;
	private String number;
	private String status;
	private String error;
	private String idx;

	public MessageResponse(String id, String points, String number, String status, String error, String idx) {
		this.id = id;
		this.points = Double.parseDouble(points);
		this.number = number;
		this.status = status;
		this.error = error;
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public double getPoints() {
		return points;
	}

	public String getNumber() {
		return number;
	}

	public String getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getIdx() {
		return idx;
	}

	public boolean isError() {
		if (id == null || id.length() == 0) {
			return true;
		}

		if (error != null && !error.isEmpty()) {
			return true;
		}

		return false;
	}

	public boolean isFinal() {
		if (isError()) {
			return true;
		}


		if (status.equals("QUEUE")) {
			return false;
		}

		if (status.equals("SENT")) {
			return false;
		}


		return true;
	}
}
