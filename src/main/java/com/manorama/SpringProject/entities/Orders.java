package com.manorama.SpringProject.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	private String orderId;
	private long item_id;
	private long user_id;
	private LocalDate date;
	private int quantity;
	private float amount;
	private String status;
	private String category;
	private String paymentStatus;

//	@ManyToMany(mappedBy = "order_items")
//	private Set<Items> items = new HashSet<>();

	public Orders(long item_id, long user_id, LocalDate date, int quantity, float amount) {
		this.item_id = item_id;
		this.user_id = user_id;
		this.date = date;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Orders(long item_id, long user_id, int quantity, float amount) {
		this.item_id = item_id;
		this.user_id = user_id;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Orders(long item_id, long user_id, int quantity) {
		this.item_id = item_id;
		this.user_id = user_id;
		this.quantity = quantity;
	}

	public Orders() {
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

	public void setId(Long id) {
		this.id = id;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
