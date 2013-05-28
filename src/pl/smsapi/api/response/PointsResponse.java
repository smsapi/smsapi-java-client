package pl.smsapi.api.response;

public class PointsResponse implements Response {

	private double points;

	public PointsResponse(String points) {

		this.points = Double.parseDouble(points);
	}

	public double getPoints() {
		return points;
	}
}
