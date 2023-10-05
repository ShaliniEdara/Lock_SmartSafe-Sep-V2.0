package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safesmart.safesmart.dto.ChangeRequestDto;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.OrderStatus;
import com.safesmart.safesmart.service.ChangeRequestService;

@RestController
@RequestMapping(value = "/change-request")
@CrossOrigin
public class ChangeRequestController {
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	
	@RequestMapping(value = "/changeRequest", method = RequestMethod.POST)
	public void createChangeRequest(@RequestBody ChangeRequestDto changeRequestDto) {
		changeRequestService.createChangeRequest(changeRequestDto);
	}

	@RequestMapping(value = "/changeRequest", method = RequestMethod.GET)
	public List<ChangeRequest> findAll() {
		return changeRequestService.findAll();
	}
	
	@RequestMapping(value = "/changeRequest/{type}/{orderStatus}", method = RequestMethod.GET)
	public ChangeRequest findByTypeAndOrderStatus(@PathVariable String type, @PathVariable String orderStatus) {
		return  changeRequestService.findByTypeAndOrderStatus(type, orderStatus);
	}
	
	// api for checking pending orders
}
