package com.manorama.SpringProject.Summary;

public class MonthlySummary {

	private int month;
	private double totalAmount;
	private int year;
	
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

	public MonthlySummary(int month, float totalAmount, int year) {
		this.month = month;
		this.totalAmount = totalAmount;
		this.year = year;
	}
	
	public MonthlySummary(int month, int year, double totalAmount) {
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
