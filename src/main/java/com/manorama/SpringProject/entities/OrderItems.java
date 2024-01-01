package com.manorama.SpringProject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "order_id")
	private Orders orders;

	@ManyToOne(optional = false)
	@JoinColumn(name = "item_id")
	private Items items;

	@Column
	private int quantity;

	public OrderItems(Orders orders, Items items, int quantity) {
		this.orders = orders;
		this.items = items;
		this.quantity = quantity;
	}

	public OrderItems() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
