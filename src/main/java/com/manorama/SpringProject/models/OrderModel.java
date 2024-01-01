package com.manorama.SpringProject.models;

import java.util.List;


public class OrderModel {
	private String category;
	private long user_id;
	private List<ItemModel> items;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public List<ItemModel> getItems() {
		return items;
	}

	public OrderModel(String category, long user_id, List<ItemModel> items) {
		this.category = category;
		this.user_id = user_id;
		this.items = items;
	}

	public OrderModel() {
		// TODO Auto-generated constructor stub
	}

	public void setItems(List<ItemModel> items) {
		this.items = items;
	}
	
	

}
