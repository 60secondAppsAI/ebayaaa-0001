package com.ebayaaa.dao;

import java.util.List;

import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


