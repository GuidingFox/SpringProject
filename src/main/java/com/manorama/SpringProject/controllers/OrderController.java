package com.manorama.SpringProject.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.models.Items;
import com.manorama.SpringProject.models.Orders;
import com.manorama.SpringProject.services.ItemsService;
import com.manorama.SpringProject.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;
	private final ItemsService itemsService;

	@Autowired
	public OrderController(OrderService orderService, ItemsService itemsService) {
		this.orderService = orderService;
		this.itemsService = itemsService;
	}

	@GetMapping
	public List<Orders> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/user/{id}")
	public List<Orders> getOrdersByUser(@PathVariable Long id) {
		System.out.println(id);
		return orderService.getOrdersByUser(id);
	}

	@PostMapping
	public void createOrder(@RequestBody Orders orders) {
		orders.setDate(LocalDate.now());
		Items item = itemsService.getItemsById(orders.getItem_id()).get();
		float amount = item.getPrice() * orders.getQuantity();
		orders.setAmount(amount);
		orderService.createOrder(orders);

	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}

	@PostMapping("/add")
	public void createOrders(@RequestBody List<Orders> orders) {
		Items item;
		float amount;
		System.out.println(orders);
		LocalDate currDate = LocalDate.now();
		for (Orders order : orders) {
			item = itemsService.getItemsById(order.getItem_id()).get();
			amount = item.getPrice() * order.getQuantity();
			order.setDate(currDate);
		}
		orderService.addOrders(orders);
	}

	@GetMapping("/summary/monthly")
	public MonthlySummary getMonthlySummary(@RequestParam long user_id) {
		return orderService.getMonthlySummary(user_id);
	}

	@GetMapping("/summary/daily")
	public DailySummary getDailySummary(@RequestParam long user_id) {
		return orderService.getDailySummary(user_id);
	}
}
