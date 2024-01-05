package com.manorama.SpringProject.models;

import java.time.LocalDate;
import java.util.Date;

public class TxnReturnModel {

	private long id;
	private LocalDate date;
	private String category;
	private String item;
	private int quantity;
	private float amount;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TxnReturnModel(long id, LocalDate date, String category, String item, int quantity, float amount) {
		this.id = id;
		this.date = date;
		this.category = category;
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}

}
