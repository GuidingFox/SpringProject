package com.manorama.SpringProject.Orders;

import java.time.LocalDate;

public class Order {
    private long id;
    private long item_id;
    private long user_id;
    private LocalDate date;
    private int quantity;

    public Order(long item_id, long user_id, LocalDate date, int quantity) {
        this.item_id = item_id;
        this.user_id = user_id;
        this.date = date;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
