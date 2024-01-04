package com.manorama.SpringProject.models;

import java.util.Date;

public class TransactionModel {

	private Date start_date;
	private Date end_date;
	private long user_id;

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public TransactionModel(Date start_date, Date end_date, long user_id) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_id = user_id;
	}

}
