package com.manorama.SpringProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manorama.SpringProject.entities.Cart;
import com.manorama.SpringProject.repositories.CartRepository;

@Service
public class CartService {
	
	private CartRepository cartRepository;
	
	
	@Autowired
	CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}



	public ResponseEntity addToOrder(Cart cart) {
		return ResponseEntity.ok(cartRepository.save(cart));
	}
	
	public ResponseEntity getCartItemsById(long id) {
		return ResponseEntity.ok(cartRepository.findAllByuserId(id));
	}

}
