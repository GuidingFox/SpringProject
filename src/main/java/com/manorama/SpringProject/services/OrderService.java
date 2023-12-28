package com.manorama.SpringProject.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.Summary.DailySummary;
import com.manorama.SpringProject.Summary.MonthlySummary;
import com.manorama.SpringProject.entities.Items;
import com.manorama.SpringProject.entities.Orders;
import com.manorama.SpringProject.models.ItemModel;
import com.manorama.SpringProject.models.OrderModel;
import com.manorama.SpringProject.repositories.ItemsRepository;
import com.manorama.SpringProject.repositories.OrderRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final ItemsRepository itemsRepository;
	

	@Autowired
	public OrderService(OrderRepository orderRepository,ItemsRepository itemsRepository) {
		this.orderRepository = orderRepository;
		this.itemsRepository = itemsRepository;
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

	public void createAnOrder(OrderModel order) {
		
		List<ItemModel> items = order.getItems();
		List<Long> itemIds = items.stream().map(item-> item.getItem_id()).collect(Collectors.toList());
		List<Items> itemsFromDb = itemsRepository.findAllById(itemIds);
	    String generatedString = RandomString.make(10);
	    List<Orders> orders = null;

		
		for (ItemModel item: items) {
			for (Items itemFromDb: itemsFromDb) {
				if (itemFromDb.getId() == item.getItem_id()) {
					Orders newOrder = new Orders();
					newOrder.setOrderId(generatedString);
					newOrder.setDate(LocalDate.now());
					newOrder.setAmount(item.getQuantity() * itemFromDb.getPrice());
					newOrder.setItem_id(item.getItem_id());
					newOrder.setQuantity(item.getQuantity());
					orders.add(newOrder);
				}
			}
		}
		orderRepository.saveAll(orders);
		
//	    for (ItemModel item: items) {
//	    	Long item_id = item.getItem_id();
//	    	Item itemFromDb = itemsRepository.findAllById(null);
//	    }
//		newOrder.set
	}
}
