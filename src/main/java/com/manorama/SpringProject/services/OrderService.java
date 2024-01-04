package com.manorama.SpringProject.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	Logger logger = LoggerFactory.getLogger(OrderService.class);

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
		return paymentService.getCheckout(totalAmt, ordItems.get(0).getOrders().getId());

	}

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

	@Transactional
	public ResponseEntity<Orders> createAnOrder(OrderModel order) {
		Orders savedOrder = orderRepository.save(new Orders(order.getUser_id(), order.getCategory()));

		List<OrderItems> orderItemsToSave = new ArrayList<>();
		for (ItemModel item : order.getItems()) {
			Optional<Items> itemFromDb = itemsRepository.findById(item.getItem_id());
			itemFromDb.ifPresent(itemEntity -> {
				OrderItems orderItem = new OrderItems(savedOrder, itemEntity, item.getQuantity());
				orderItemsToSave.add(orderItem);
			});
		}
		return ResponseEntity.ok(savedOrder);
	}

	@Transactional
	public ResponseEntity createOrderFromCart(OrderModel order) {
		Orders savedOrder = orderRepository.save(new Orders(order.getUser_id(), order.getCategory()));

		List<OrderItems> orderItemsToSave = new ArrayList<>();
		for (ItemModel item : order.getItems()) {
			Optional<Items> itemFromDb = itemsRepository.findById(item.getItem_id());
			itemFromDb.ifPresent(itemEntity -> {
				OrderItems orderItem = new OrderItems(savedOrder, itemEntity, item.getQuantity());
				orderItemsToSave.add(orderItem);
			});
		}
		orderItemRepository.saveAll(orderItemsToSave);
		return createCheckout(savedOrder.getId());
//		return savedOrder;

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

	public ResponseEntity getAdminSummary() {
		MonthlySummary ms = orderRepository.adminSummary();
		DailySummary ds = orderRepository.adminDailySummary();
		Summary sm = new Summary(ms, ds);
		return ResponseEntity.ok(sm);
	}

	public ResponseEntity<Object> deleteOrderItems(Long order_id, Long item_id) {
		try {
			List<OrderItems> ot = orderItemRepository.findAllByOrders(orderRepository.findById(order_id).get());
			for (OrderItems ordItem : ot) {
				if (ordItem.getItems().getId() == item_id) {
					orderItemRepository.deleteById(ordItem.getId());
					return ResponseEntity.ok().build();
				}
			}
		} catch (NoSuchElementException ne) {
			logger.error("failed to remove item: {}", ne.getMessage());
			return ResponseEntity.status(422).body("no such item exists");
		} catch (Exception e) {
			logger.error("some error occurred: {}", e.getMessage());
			return ResponseEntity.status(500).body("some error occurred: " + e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	public ResponseEntity updateOrderItems(long order_id, long item_id, int quantity) {

		try {
			List<OrderItems> ot = orderItemRepository.findAllByOrders(orderRepository.findById(order_id).get());
			for (OrderItems ordItem : ot) {
				if (ordItem.getItems().getId() == item_id) {
					ordItem.setQuantity(quantity);
					orderItemRepository.save(ordItem);
					return ResponseEntity.ok().build();
				}
			}
		} catch (NoSuchElementException ne) {
			logger.error("failed to remove item: {}", ne.getMessage());
			return ResponseEntity.status(422).body("no such item exists");
		} catch (Exception e) {
			logger.error("some error occurred: {}", e.getMessage());
			return ResponseEntity.status(500).body("some error occurred: " + e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Orders> getOrderById(Long order_id) {
		Optional<Orders> order = orderRepository.findById(order_id);
		if (order.isPresent()) {
			return ResponseEntity.ok(order.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	public ResponseEntity updateOnPayment(long order_id) {
		Optional<Orders> order = orderRepository.findById(order_id);
		if (order.isPresent()) {
			order.get().setPaymentStatus("success");
			order.get().setStatus("fulfilled");
		}
//		orderRepository.save(order.get());
		return ResponseEntity.ok(orderRepository.save(order.get()));
//		return null;
	}
}
