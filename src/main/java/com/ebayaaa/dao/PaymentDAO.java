package com.ebayaaa.dao;

import java.util.List;

import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


