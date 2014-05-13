package pl.smsapi.api.response;

public class PointsResponse implements Response {

	private double points;

	public PointsResponse(double points) {

		this.points = points;
	}

	public double getPoints() {
		return points;
	}
}
