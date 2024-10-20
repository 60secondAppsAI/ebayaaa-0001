package com.ebayaaa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ebayaaa.domain.ItemCategory;
import com.ebayaaa.dto.ItemCategoryDTO;
import com.ebayaaa.dto.ItemCategorySearchDTO;
import com.ebayaaa.dto.ItemCategoryPageDTO;
import com.ebayaaa.dto.ItemCategoryConvertCriteriaDTO;
import com.ebayaaa.service.GenericService;
import com.ebayaaa.dto.common.RequestDTO;
import com.ebayaaa.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItemCategoryService extends GenericService<ItemCategory, Integer> {

	List<ItemCategory> findAll();

	ResultDTO addItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO);

	ResultDTO updateItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO);

    Page<ItemCategory> getAllItemCategorys(Pageable pageable);

    Page<ItemCategory> getAllItemCategorys(Specification<ItemCategory> spec, Pageable pageable);

	ResponseEntity<ItemCategoryPageDTO> getItemCategorys(ItemCategorySearchDTO itemCategorySearchDTO);
	
	List<ItemCategoryDTO> convertItemCategorysToItemCategoryDTOs(List<ItemCategory> itemCategorys, ItemCategoryConvertCriteriaDTO convertCriteria);

	ItemCategoryDTO getItemCategoryDTOById(Integer itemCategoryId);







}





