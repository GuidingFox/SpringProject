package com.manorama.SpringProject.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		List<ItemModel> items = cartItems.stream().map(cItem->{
			return new ItemModel(cItem.getItem_id(), cItem.getQuantity());
		}).collect(Collectors.toList());

		OrderModel orders = new OrderModel(cartItems.get(0).getCategory(), user_id, items);
		return orderService.createOrderFromCart(orders);
//		return ResponseEntity.ok(order);
	}

	public ResponseEntity addItemToCart(Cart cart) {

		Optional<Cart> cartItems = cartRepository.findByUserIdandItemId(cart.getUserId(), cart.getItem_id());
		if (cartItems.isEmpty()) {
			Cart savedCart = cartRepository.save(cart);
			return ResponseEntity.ok(savedCart);
		} else {
			return ResponseEntity.status(208).body("CART_ITEM_ALREADY_EXITS");
		}
//		return ResponseEntity.internalServerError().build();
	}

	public ResponseEntity deleteFromCart(long user_id, long item_id) {
		Optional<Cart> cartItems = cartRepository.findByUserIdandItemId(user_id, item_id);
		if (cartItems.isEmpty()) {
			return ResponseEntity.status(204).body("CART_ITEM_DOESNT_EXIST");
		} else {
			cartRepository.delete(cartItems.get());
			return ResponseEntity.ok("deletion successful");
		}
	}

	public ResponseEntity updateCart(long user_id, long item_id, int quantity) {
		Optional<Cart> cartItems = cartRepository.findByUserIdandItemId(user_id, item_id);
		if (cartItems.isEmpty()) {
			return ResponseEntity.status(204).body("CART_ITEM_DOESNT_EXIST");
		} else {
			cartItems.get().setQuantity(quantity);
			cartRepository.save(cartItems.get());
			return ResponseEntity.ok("updation successful");
		}
	}

}
