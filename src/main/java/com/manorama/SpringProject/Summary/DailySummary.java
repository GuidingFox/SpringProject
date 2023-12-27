package com.manorama.SpringProject.Summary;

public class DailySummary {

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	private long user_id;
	private int day;
	private float totalAmount;

	public DailySummary(long user_id, int day, float totalAmount) {
		this.user_id = user_id;
		this.day = day;
		this.totalAmount = totalAmount;
	}
}
