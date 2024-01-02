package com.manorama.SpringProject.services;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.Summary.Summary;
import com.manorama.SpringProject.entities.Items;
import com.manorama.SpringProject.entities.OrderItems;
import com.manorama.SpringProject.entities.Orders;
import com.manorama.SpringProject.models.ItemModel;
import com.manorama.SpringProject.models.OrderModel;
import com.manorama.SpringProject.repositories.ItemsRepository;
import com.manorama.SpringProject.repositories.OrderItemRepository;
import com.manorama.SpringProject.repositories.OrderRepository;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final ItemsRepository itemsRepository;
	private final OrderItemRepository orderItemRepository;
	private final PaymentService paymentService;

	@Autowired
	public OrderService(OrderRepository orderRepository, ItemsRepository itemsRepository,
			OrderItemRepository orderItemRepository, PaymentService paymentService) {
		this.orderRepository = orderRepository;
		this.itemsRepository = itemsRepository;
		this.orderItemRepository = orderItemRepository;
		this.paymentService = paymentService;
	}

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Orders> getOrdersByUser(Long id) {
		return orderRepository.findById(id);
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

	public ResponseEntity createCheckout(Long id) {
		List<OrderItems> ordItems = orderItemRepository.findAllByOrders(orderRepository.findById(id).get());
		float totalAmt = 0;
		for (OrderItems ordItem : ordItems) {
			totalAmt += ordItem.getItems().getPrice() * ordItem.getQuantity();
		}
		return paymentService.getCheckout(totalAmt);

	}
//	public MonthlySummary getMonthlySummary(long user_id) {
//		return orderRepository.getUserMonthlySummary(user_id);
//	}

	public MonthlySummary getMonthlySummary(long user_id) {
		MonthlySummary data = orderRepository.userSummary(user_id);
		return data;
	}

	public DailySummary getDailySummary(long user_id) {
		return orderRepository.userDailySummary(user_id);
	}

	public Optional<Orders> getOrderTest(Long orderId) {
		Optional<Orders> order = orderRepository.findById(orderId);
		return order;
	}

	public void createAnOrder(OrderModel order) {
		Orders savedOrder = orderRepository.save(new Orders(order.getUser_id(), order.getCategory()));
		List<ItemModel> items = order.getItems();
		items.forEach(item -> {
			Optional<Items> itemFromDb = itemsRepository.findById(item.getItem_id());
			if (itemFromDb.isPresent()) {
				orderItemRepository.save(new OrderItems(savedOrder, itemFromDb.get(), item.getQuantity()));
			}
		});
	}

	public ResponseEntity getDailyOrders() {
		return ResponseEntity.ok(orderRepository.findAllByDate(new Date()));
	}
	
	public ResponseEntity getSummary(long user_id) {
		MonthlySummary ms = orderRepository.userSummary(user_id);
		DailySummary ds = orderRepository.userDailySummary(user_id);
		Summary sm = new Summary(ms, ds);
		return ResponseEntity.ok(sm);
	}
}
