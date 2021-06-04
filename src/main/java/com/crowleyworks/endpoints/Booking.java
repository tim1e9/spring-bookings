package com.crowleyworks.endpoints;

public class Booking {

	private long id;
	private long charterId;
	private String customerName;
	private String comments;

	public Booking(long id, long charterId, String customerName, String comments) {
		this.id = id;
		this.charterId = charterId;
		this.customerName = customerName;
		this.comments = comments;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCharterId() {
		return charterId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getComments() {
		return comments;
	}

	public String toString() {
		return "Charter: " + charterId + " customer: " + customerName + " comments: " + comments;
	}
}