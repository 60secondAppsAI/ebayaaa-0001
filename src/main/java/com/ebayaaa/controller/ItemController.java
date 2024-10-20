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

import com.ebayaaa.domain.Item;
import com.ebayaaa.dto.ItemDTO;
import com.ebayaaa.dto.ItemSearchDTO;
import com.ebayaaa.dto.ItemPageDTO;
import com.ebayaaa.service.ItemService;
import com.ebayaaa.dto.common.RequestDTO;
import com.ebayaaa.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/item")
@RestController
public class ItemController {

	private final static Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	ItemService itemService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Item> getAll() {

		List<Item> items = itemService.findAll();
		
		return items;	
	}

	//@ReadAccess
	@GetMapping(value = "/{itemId}")
	@ResponseBody
	public ItemDTO getItem(@PathVariable Integer itemId) {
		
		return (itemService.getItemDTOById(itemId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ResponseEntity<?> addItem(@RequestBody ItemDTO itemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = itemService.addItem(itemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/items")
	public ResponseEntity<ItemPageDTO> getItems(ItemSearchDTO itemSearchDTO) {
 
		return itemService.getItems(itemSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public ResponseEntity<?> updateItem(@RequestBody ItemDTO itemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = itemService.updateItem(itemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
