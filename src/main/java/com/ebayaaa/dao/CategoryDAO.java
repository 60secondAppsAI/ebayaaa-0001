package com.ebayaaa.dao;

import java.util.List;

import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


