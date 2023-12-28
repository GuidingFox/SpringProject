package com.manorama.SpringProject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manorama.SpringProject.services.PaymentService;

@RestController
@RequestMapping("/api/pay")
public class PaymentsController {

	private PaymentService paymentService;
	
	

	public PaymentsController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	@PostMapping("/checkout")
	public ResponseEntity<Object> createCheckoutSession() {
		return this.paymentService.getCheckout(100);
	}
}