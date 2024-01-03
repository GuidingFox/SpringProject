package com.manorama.SpringProject.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	private String name;
	private String category;
	private float price;

	@OneToMany(mappedBy = "items")
	private Set<OrderItems> orders = new HashSet<>();

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	private float quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isIncomplete() {
		if (this.getCategory() == null || this.getName() == null || this.getPrice() == 0 || this.getQuantity() == 0) {
			return true;
		} else if (this.getCategory() == "" || this.getName() == "" || this.getPrice() == 0
				|| this.getQuantity() == 0) {
			return true;
		} else {
			return false;
		}

	}
}
