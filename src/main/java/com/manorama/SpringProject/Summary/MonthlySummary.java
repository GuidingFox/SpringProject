package com.manorama.SpringProject.Summary;

public class MonthlySummary {

	private int month;
	private double totalAmount;
	private int year;
	private long totalOrders;
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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

	public MonthlySummary(int month, float totalAmount, int year, long totalOrders) {
		this.month = month;
		this.totalAmount = totalAmount;
		this.year = year;
		this.totalOrders = totalOrders;
	}
	
	public MonthlySummary(int month, int year, double totalAmount, long totalOrders) {
		this.month = month;
		this.totalAmount = totalAmount;
		this.year = year;
		this.totalOrders = totalOrders;

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}
}
