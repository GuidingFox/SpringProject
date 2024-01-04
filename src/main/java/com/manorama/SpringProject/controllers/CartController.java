package com.manorama.SpringProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.entities.Cart;
import com.manorama.SpringProject.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping
	public ResponseEntity addToCart(@RequestBody Cart cart) {
		return cartService.addToOrder(cart);
	}

	@GetMapping
	public ResponseEntity getCartItems(@RequestParam long user_id) {
		return cartService.getCartItemsById(user_id);
	}
	
	public ResponseEntity cartCheckout(@RequestParam long user_id) {
		return null;

	}
}
