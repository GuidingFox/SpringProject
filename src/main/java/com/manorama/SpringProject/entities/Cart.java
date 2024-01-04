package com.manorama.SpringProject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private long userId;
	private int quantity;
	private float price;
	private long item_id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public Cart(long userId, int quantity, float price, long item_id) {
		this.userId = userId;
		this.quantity = quantity;
		this.price = price;
		this.item_id = item_id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Cart() {
	}

}
