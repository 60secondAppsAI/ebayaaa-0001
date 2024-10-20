package com.ebayaaa.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.ebayaaa.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.ebayaaa.domain.Payment;
import com.ebayaaa.dto.PaymentDTO;
import com.ebayaaa.dto.PaymentSearchDTO;
import com.ebayaaa.dto.PaymentPageDTO;
import com.ebayaaa.service.PaymentService;
import com.ebayaaa.dto.common.RequestDTO;
import com.ebayaaa.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/payment")
@RestController
public class PaymentController {

	private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentService paymentService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Payment> getAll() {

		List<Payment> payments = paymentService.findAll();
		
		return payments;	
	}

	//@ReadAccess
	@GetMapping(value = "/{paymentId}")
	@ResponseBody
	public PaymentDTO getPayment(@PathVariable Integer paymentId) {
		
		return (paymentService.getPaymentDTOById(paymentId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public ResponseEntity<?> addPayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentService.addPayment(paymentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/payments")
	public ResponseEntity<PaymentPageDTO> getPayments(PaymentSearchDTO paymentSearchDTO) {
 
		return paymentService.getPayments(paymentSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public ResponseEntity<?> updatePayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentService.updatePayment(paymentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}