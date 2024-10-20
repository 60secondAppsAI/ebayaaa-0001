package com.ebayaaa.dao;

import java.util.List;

import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.domain.ItemCategory;





public interface ItemCategoryDAO extends GenericDAO<ItemCategory, Integer> {
  
	List<ItemCategory> findAll();
	






}


