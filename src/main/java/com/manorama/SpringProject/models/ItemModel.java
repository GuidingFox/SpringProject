package com.manorama.SpringProject.models;


public class ItemModel {
	private long item_id;
	private int quantity;

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ItemModel(long item_id, int quantity) {
		this.item_id = item_id;
		this.quantity = quantity;
	}
	
	
}