package com.manorama.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.models.Orders;
import com.manorama.SpringProject.repositories.OrderRepository;

@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Orders> getOrdersByUser(Long id) {
		return orderRepository.findAllById(List.of(id));
	}

	public void createOrder(Orders order) {
		orderRepository.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	public void addOrders(List<Orders> orders) {
		orderRepository.saveAll(orders);
	}

	public MonthlySummary getMonthlySummary(long user_id) {
		return orderRepository.getUserMonthlySummary(user_id);
	}

	public DailySummary getDailySummary(long user_id) {
		return orderRepository.getUserDailySummary(user_id);
	}
}
