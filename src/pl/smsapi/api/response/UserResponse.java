package pl.smsapi.api.response;

public class UserResponse implements Response {

	private String username;
	private double limit;
	private double monthLimit;
	private int senders;
	private int phonebook;
	private int active;
	private String info;

	public UserResponse(String username, double limit, double month_limit, int senders, int phonebook, int active, String info) {
		this.username = username;
		this.limit = limit;
		this.monthLimit = month_limit;
		this.senders = senders;
		this.phonebook = phonebook;
		this.active = active;
		this.info = info;
	}

	public String getUsername() {
		return username;
	}

	public double getLimit() {
		return limit;
	}

	public double getMonthLimit() {
		return monthLimit;
	}

	public int getSenders() {
		return senders;
	}

	public int getPhonebook() {
		return phonebook;
	}

	public int getActive() {
		return active;
	}

	public String getInfo() {
		return info;
	}
}
