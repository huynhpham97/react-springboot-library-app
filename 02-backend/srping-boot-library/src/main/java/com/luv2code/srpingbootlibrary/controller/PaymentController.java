package com.luv2code.srpingbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.srpingbootlibrary.requestmodels.PaymentInfoRequest;
import com.luv2code.srpingbootlibrary.service.PaymentService;
import com.luv2code.srpingbootlibrary.utils.ExtractJWT;
import com.luv2code.srpingbootlibrary.utils.LibraryCommonUtils;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
			throws StripeException {

		PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
		String paymentStr = paymentIntent.toJson();

		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}

	@PutMapping("/payment-complete")
	public ResponseEntity<String> stringPaymentComplete(@RequestHeader(value = "Authorization") String token)
			throws Exception {

		String userEmal = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);

		if (userEmal == null) {
			throw new Exception("User email is missing !");
		}
		return paymentService.stripePayment(userEmal);
	}
}
