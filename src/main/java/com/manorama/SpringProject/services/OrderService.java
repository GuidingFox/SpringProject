package com.manorama.SpringProject.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	public OrderService(OrderRepository orderRepository, ItemsRepository itemsRepository,
			OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.itemsRepository = itemsRepository;
		this.orderItemRepository = orderItemRepository;
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

//	@Transactional
//	public void deleteOrder(Long orderId) {
////		orderRepository.deleteById(orderId);
//		try {
//			Orders order = orderRepository.findById(orderId).get();
//			System.out.println(order.getId());
//			List<OrderItems> orderItems = orderItemRepository.findAllByOrders(order);
//			orderItems.forEach(ordItem -> {
//				System.out.println(ordItem.getId());
//				ordItem.setOrders(null);
//				ordItem.setItems(null);
//			});
//			orderItemRepository.saveAll(orderItems);
//			System.out.println("test");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public MonthlySummary getMonthlySummary(long user_id) {
//		return orderRepository.getUserMonthlySummary(user_id);
//	}
//
//	public DailySummary getDailySummary(long user_id) {
//		return orderRepository.getUserDailySummary(user_id);
//	}

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
				System.out.println(itemFromDb.get().getName());
				orderItemRepository.save(new OrderItems(savedOrder, itemFromDb.get(), item.getQuantity()));
			}
		});
	}
}
