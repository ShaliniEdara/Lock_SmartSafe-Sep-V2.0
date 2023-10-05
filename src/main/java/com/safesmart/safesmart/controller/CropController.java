package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.CropRequest;
import com.safesmart.safesmart.dto.CropResponse;
import com.safesmart.safesmart.dto.PrinterRequest;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.service.CropService;
import com.safesmart.safesmart.service.PrinterService;

@RestController
@RequestMapping(value = "/crop")
@CrossOrigin
public class CropController {
	@Autowired
	private CropService cropService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody CropRequest cropRequest) {
		cropRequest.validateRequiredAttributes();
		cropService.add(cropRequest);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CropResponse> findAllEmployee() {
		return cropService.findAllUser();
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByCrop(@PathVariable("Id") Long Id) {
		cropService.deleteByCrop(Id);
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public void updateCrop(@PathVariable("Id") Long Id, @RequestBody CropRequest cropRequest) {
		cropRequest.setId(Id);
		cropService.updateCrop(cropRequest);
	}

}
