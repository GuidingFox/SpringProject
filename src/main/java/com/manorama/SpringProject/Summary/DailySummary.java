package com.manorama.SpringProject.Summary;

public class DailySummary {
	
	private int day;
	private int month;
	private int year;
	private double totalAmount;


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


	public DailySummary(int day, float totalAmount) {
		this.day = day;
		this.totalAmount = totalAmount;
	}

	public DailySummary(int day, int month, int year, double totalAmount) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.totalAmount = totalAmount;
	}
	
	
}
