package pl.smsapi.message;

import java.util.ArrayList;

public final class Result {

	protected String id;
	protected String points;
	protected String response;
	protected String request;
	protected String status;
	protected Error error;
	protected String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points.trim();
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {

		if (error != null) {
			return error.toString();
		}

		return "";
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone.trim();
	}

	public static void renderResultMessage(ArrayList<Result> result, String message, String response, String request) {

		String[] partsRowArr = message.split(";");

		for (String row : partsRowArr) {
			Result res = new Result();

			String[] partsCollArr = row.split(":");

			if (partsCollArr.length == 0) {
				throw new IllegalArgumentException("Incorrect result message from api");
			}

			if (partsCollArr[0].equals("OK")) {

				res.setStatus("OK");
				res.setId(partsCollArr[1]);
				res.setPoints(partsCollArr[2]);
				if (partsCollArr.length == 4) {
					res.setPhone(partsCollArr[3]);
				}

			} else if (partsCollArr[0].equals("ERROR")) {

				res.setStatus("ERROR");
				Error error = new Error(partsCollArr[1]);
				res.setError(error);
			}

			res.setResponse(response);
			res.setRequest(request);

			result.add(res);
		}

	}
}

final class Error {

	private String number;

	public Error(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return (number.isEmpty() == true) ? "" : number.trim();
	}
}
