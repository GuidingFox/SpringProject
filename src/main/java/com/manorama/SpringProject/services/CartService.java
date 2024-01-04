package com.manorama.SpringProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.entities.Cart;
import com.manorama.SpringProject.entities.Orders;
import com.manorama.SpringProject.models.ItemModel;
import com.manorama.SpringProject.models.OrderModel;
import com.manorama.SpringProject.repositories.CartRepository;

@Service
public class CartService {

	private CartRepository cartRepository;
	private OrderService orderService;

	@Autowired
	CartService(CartRepository cartRepository, OrderService orderService) {
		this.cartRepository = cartRepository;
		this.orderService = orderService;
	}

	public ResponseEntity addToOrder(Cart cart) {
		return ResponseEntity.ok(cartRepository.save(cart));
	}

	public ResponseEntity getCartItemsById(long id) {
		return ResponseEntity.ok(cartRepository.findAllByuserId(id));
	}

	public ResponseEntity createCheckout(long user_id) {
		List<Cart> cartItems = cartRepository.findAllByuserId(user_id);
		List<ItemModel> items = null;
		cartItems.forEach(cItem -> {
			items.add(new ItemModel(cItem.getItem_id(), cItem.getQuantity()));
		});

		OrderModel orders = new OrderModel(cartItems.get(0).getCategory(), user_id, items);
		Orders order = orderService.createOrderFromCart(orders);
		return ResponseEntity.ok(order);
	}
}
