package com.manorama.SpringProject.Summary;

public class DailySummary {
	
	private long user_id;
	private int day;
	private int month;
	private int year;
	private double totalAmount;

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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public DailySummary(long user_id, int day, float totalAmount) {
		this.user_id = user_id;
		this.day = day;
		this.totalAmount = totalAmount;
	}

	public DailySummary(int day, int month, int year, double totalAmount, long user_id) {
		this.user_id = user_id;
		this.day = day;
		this.month = month;
		this.year = year;
		this.totalAmount = totalAmount;
	}
	
	
}
