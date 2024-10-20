package com.ebayaaa.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.ebayaaa.dao.GenericDAO;
import com.ebayaaa.service.GenericService;
import com.ebayaaa.service.impl.GenericServiceImpl;
import com.ebayaaa.dao.ItemCategoryDAO;
import com.ebayaaa.domain.ItemCategory;
import com.ebayaaa.dto.ItemCategoryDTO;
import com.ebayaaa.dto.ItemCategorySearchDTO;
import com.ebayaaa.dto.ItemCategoryPageDTO;
import com.ebayaaa.dto.ItemCategoryConvertCriteriaDTO;
import com.ebayaaa.dto.common.RequestDTO;
import com.ebayaaa.dto.common.ResultDTO;
import com.ebayaaa.service.ItemCategoryService;
import com.ebayaaa.util.ControllerUtils;





@Service
public class ItemCategoryServiceImpl extends GenericServiceImpl<ItemCategory, Integer> implements ItemCategoryService {

    private final static Logger logger = LoggerFactory.getLogger(ItemCategoryServiceImpl.class);

	@Autowired
	ItemCategoryDAO itemCategoryDao;

	


	@Override
	public GenericDAO<ItemCategory, Integer> getDAO() {
		return (GenericDAO<ItemCategory, Integer>) itemCategoryDao;
	}
	
	public List<ItemCategory> findAll () {
		List<ItemCategory> itemCategorys = itemCategoryDao.findAll();
		
		return itemCategorys;	
		
	}

	public ResultDTO addItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO) {

		ItemCategory itemCategory = new ItemCategory();

		itemCategory.setItemCategoryId(itemCategoryDTO.getItemCategoryId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		itemCategory = itemCategoryDao.save(itemCategory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ItemCategory> getAllItemCategorys(Pageable pageable) {
		return itemCategoryDao.findAll(pageable);
	}

	public Page<ItemCategory> getAllItemCategorys(Specification<ItemCategory> spec, Pageable pageable) {
		return itemCategoryDao.findAll(spec, pageable);
	}

	public ResponseEntity<ItemCategoryPageDTO> getItemCategorys(ItemCategorySearchDTO itemCategorySearchDTO) {
	
			Integer itemCategoryId = itemCategorySearchDTO.getItemCategoryId(); 
 			String sortBy = itemCategorySearchDTO.getSortBy();
			String sortOrder = itemCategorySearchDTO.getSortOrder();
			String searchQuery = itemCategorySearchDTO.getSearchQuery();
			Integer page = itemCategorySearchDTO.getPage();
			Integer size = itemCategorySearchDTO.getSize();

	        Specification<ItemCategory> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, itemCategoryId, "itemCategoryId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<ItemCategory> itemCategorys = this.getAllItemCategorys(spec, pageable);
		
		//System.out.println(String.valueOf(itemCategorys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(itemCategorys.getTotalPages()));
		
		List<ItemCategory> itemCategorysList = itemCategorys.getContent();
		
		ItemCategoryConvertCriteriaDTO convertCriteria = new ItemCategoryConvertCriteriaDTO();
		List<ItemCategoryDTO> itemCategoryDTOs = this.convertItemCategorysToItemCategoryDTOs(itemCategorysList,convertCriteria);
		
		ItemCategoryPageDTO itemCategoryPageDTO = new ItemCategoryPageDTO();
		itemCategoryPageDTO.setItemCategorys(itemCategoryDTOs);
		itemCategoryPageDTO.setTotalElements(itemCategorys.getTotalElements());
		return ResponseEntity.ok(itemCategoryPageDTO);
	}

	public List<ItemCategoryDTO> convertItemCategorysToItemCategoryDTOs(List<ItemCategory> itemCategorys, ItemCategoryConvertCriteriaDTO convertCriteria) {
		
		List<ItemCategoryDTO> itemCategoryDTOs = new ArrayList<ItemCategoryDTO>();
		
		for (ItemCategory itemCategory : itemCategorys) {
			itemCategoryDTOs.add(convertItemCategoryToItemCategoryDTO(itemCategory,convertCriteria));
		}
		
		return itemCategoryDTOs;

	}
	
	public ItemCategoryDTO convertItemCategoryToItemCategoryDTO(ItemCategory itemCategory, ItemCategoryConvertCriteriaDTO convertCriteria) {
		
		ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();
		
		itemCategoryDTO.setItemCategoryId(itemCategory.getItemCategoryId());

	

		
		return itemCategoryDTO;
	}

	public ResultDTO updateItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO) {
		
		ItemCategory itemCategory = itemCategoryDao.getById(itemCategoryDTO.getItemCategoryId());

		itemCategory.setItemCategoryId(ControllerUtils.setValue(itemCategory.getItemCategoryId(), itemCategoryDTO.getItemCategoryId()));



        itemCategory = itemCategoryDao.save(itemCategory);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ItemCategoryDTO getItemCategoryDTOById(Integer itemCategoryId) {
	
		ItemCategory itemCategory = itemCategoryDao.getById(itemCategoryId);
			
		
		ItemCategoryConvertCriteriaDTO convertCriteria = new ItemCategoryConvertCriteriaDTO();
		return(this.convertItemCategoryToItemCategoryDTO(itemCategory,convertCriteria));
	}







}
