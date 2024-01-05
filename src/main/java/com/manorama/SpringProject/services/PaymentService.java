package com.manorama.SpringProject.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;

@Service
public class PaymentService {

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

	private String getRequestString(float amount, int quantity, String url, long order_id) {
		StringBuilder req = new StringBuilder();
		req.append("mode=payment&");
		req.append("success_url=" + url + "?order_id=" + order_id + "&");
		req.append("cancel_url=" + url + "?canceled=True&");
		req.append("line_items[0][price_data][unit_amount]=" + (int) amount * 100 + "&");
		req.append("line_items[0][price_data][product_data][name]=canteenpayment&");
		req.append("line_items[0][price_data][currency]=inr&");
		req.append("line_items[0][quantity]=" + quantity + "&");
		req.append("billing_address_collection=required");
		return req.toString();
	}

	public ResponseEntity getCheckout(float amount, long order_id) {
		Stripe.apiKey = System.getenv("STRIPE_KEY");
		String YOUR_DOMAIN = System.getenv("SITE_DOMAIN");
		String reqData = getRequestString(amount, 1, YOUR_DOMAIN, order_id);
		HttpResponse<String> response;
		try {
			response = sendHttpRequest(reqData);
			return ResponseEntity.ok(response.body());

		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException | IOException
				| InterruptedException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(null);
		}

	}
}
