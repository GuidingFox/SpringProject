package com.manorama.SpringProject.Summary;

public class Summary {
	MonthlySummary monthlySummary;
	DailySummary dailySummary;

	public MonthlySummary getMonthlySummary() {
		return monthlySummary;
	}

	public void setMonthlySummary(MonthlySummary monthlySummary) {
		this.monthlySummary = monthlySummary;
	}

	public DailySummary getDailySummary() {
		return dailySummary;
	}

	public void setDailySummary(DailySummary dailySummary) {
		this.dailySummary = dailySummary;
	}

	public Summary(MonthlySummary monthlySummary, DailySummary dailySummary) {
		this.monthlySummary = monthlySummary;
		this.dailySummary = dailySummary;
	}
	
	
}
