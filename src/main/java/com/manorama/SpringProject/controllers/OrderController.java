package com.manorama.SpringProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.entities.Orders;
import com.manorama.SpringProject.models.OrderModel;
import com.manorama.SpringProject.models.UpdateOrder;
import com.manorama.SpringProject.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<Orders> getAllOrders() {
		return orderService.getAllOrders();
	}

	@PutMapping("/order/delete-items")
	public ResponseEntity deleteOrderItems(@RequestParam long order_id, long item_id) {
		return orderService.deleteOrderItems(order_id, item_id);
	}
	
	@PutMapping("/order/update-items")
	public ResponseEntity updateOrderItems(@RequestBody UpdateOrder order) {
		return orderService.updateOrderItems(order.getOrder_id(), order.getItem_id(), order.getQuantity());
	}

	@GetMapping("/{id}")
	public Optional<Orders> getOrdersByUser(@PathVariable Long id) {
		return orderService.getOrderTest(id);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}

	@PostMapping("/add")
	public void createOrder(@RequestBody OrderModel order) {
		orderService.createAnOrder(order);
	}

	@PostMapping("/checkout")
	public ResponseEntity createCheckout(@RequestParam String order_id) {

		System.out.println(order_id);
		return orderService.createCheckout(Long.parseLong(order_id));
	}

	@GetMapping("/today")
	public ResponseEntity getDailyOrders() {
		return orderService.getDailyOrders();

	}

	@GetMapping("/summary")
	public ResponseEntity getSummary(@RequestParam long user_id) {
		return orderService.getSummary(user_id);
	}

}
