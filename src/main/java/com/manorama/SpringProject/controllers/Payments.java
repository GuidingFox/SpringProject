package com.manorama.SpringProject.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;

@RestController
@RequestMapping("/api/pay")
public class Payments {

	public HttpResponse<String> sendHttpRequest(String obj) throws KeyManagementException, NoSuchAlgorithmException,
			URISyntaxException, IOException, InterruptedException {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		};
		sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());

		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://api.stripe.com/v1/checkout/sessions"))
				.header("Authorization", "Bearer " + Stripe.apiKey)
				.header("content-type", "application/x-www-form-urlencoded")
				.POST(HttpRequest.BodyPublishers.ofString(obj)).build();

		HttpClient client = HttpClient.newBuilder().sslContext(sslContext).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;

	}

	
	private String getRequestString(float amount, int quantity, String url) {
		StringBuilder req = new StringBuilder();
		req.append("mode=payment&");
		req.append("success_url=" + url + "?success=True&");
		req.append("cancel_url=" + url + "?canceled=True&");
		req.append("line_items[0][price_data][unit_amount]=" + (int) amount * 100 + "&");
		req.append("line_items[0][price_data][product_data][name]=canteenpayment&");
		req.append("line_items[0][price_data][currency]=inr&");
		req.append("line_items[0][quantity]=" + quantity + "&");
		return req.toString();
		
	}

	@PostMapping("/checkout")
	public ResponseEntity<Object> createCheckoutSession() throws StripeException, IOException, InterruptedException,
			KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		Stripe.apiKey = "sk_test_51OPIddSBY2c1xYHV84jmsahFjlMNE09nOJZxU7y87yM0NdWlo6JZlptYnVZPt7075DgLEgXz0bHNi8cmvIeYrVLX00W2DrcWXU";
		String YOUR_DOMAIN = "http://localhost:3000";
		String reqData = getRequestString(123, 1, YOUR_DOMAIN);

		HttpResponse<String> response = sendHttpRequest(reqData);

		System.out.println("Response Code: " + response.statusCode());
		System.out.println("Response Body: " + response.body());
		
		return ResponseEntity.ok().body(response.body());

	}
}