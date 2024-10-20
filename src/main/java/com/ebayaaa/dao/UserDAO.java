package com.ebayaaa.dao;

import java.util.List;

import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


