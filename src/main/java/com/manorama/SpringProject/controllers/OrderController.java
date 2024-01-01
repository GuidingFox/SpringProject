package com.manorama.SpringProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.entities.OrderItems;
import com.manorama.SpringProject.entities.Orders;
import com.manorama.SpringProject.models.OrderModel;
import com.manorama.SpringProject.services.ItemsService;
import com.manorama.SpringProject.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;
//	private final ItemsService itemsService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
//		this.itemsService = itemsService;
	}

	@GetMapping
	public List<Orders> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public Optional<Orders> getOrdersByUser(@PathVariable Long id) {
		return orderService.getOrderTest(id);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
//		orderService
	}

	@PostMapping("/add")
	public void createOrder(@RequestBody OrderModel order) {
		orderService.createAnOrder(order);
	}

//	@GetMapping("/summary/monthly")
//	public MonthlySummary getMonthlySummary(@RequestParam long user_id) {
//		return orderService.getMonthlySummary(user_id);
//	}
//
//	@GetMapping("/summary/daily")
//	public DailySummary getDailySummary(@RequestParam long user_id) {
//		return orderService.getDailySummary(user_id);
//	}
}
