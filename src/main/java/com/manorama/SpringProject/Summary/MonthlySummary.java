package com.manorama.SpringProject.Summary;

public class MonthlySummary {
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public int getDay() {
		return month;
	}

	public void setDay(int day) {
		this.month = day;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	private long user_id;
	private int month;
	private double totalAmount;
	private int year;

	public MonthlySummary(long user_id, int month, float totalAmount, int year) {
		this.user_id = user_id;
		this.month = month;
		this.totalAmount = totalAmount;
		this.year = year;
	}
	
	public MonthlySummary(int month, int year, double totalAmount, long user_id) {
		this.user_id = user_id;
		this.month = month;
		this.totalAmount = totalAmount;
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
