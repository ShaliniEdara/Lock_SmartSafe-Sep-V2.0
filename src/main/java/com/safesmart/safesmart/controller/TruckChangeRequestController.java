package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.TruckChangeRequestDto;
import com.safesmart.safesmart.model.TruckChangeRequest;
import com.safesmart.safesmart.service.TruckChangeRequestService;

@RestController
@RequestMapping(value = "/truck-change-request")
@CrossOrigin
public class TruckChangeRequestController {
	
	@Autowired
	private TruckChangeRequestService truckChangeRequestService;
	
	@RequestMapping(value = "/addtruckchangerequest", method = RequestMethod.POST)
	public void createChangeRequest(@RequestBody TruckChangeRequestDto truckChangeRequestDto) {
		truckChangeRequestService.createTruckChangeRequest(truckChangeRequestDto);
	}

	@RequestMapping(value = "/truck-change-request/all", method = RequestMethod.GET)
	public List<TruckChangeRequest> findAll() {
		return truckChangeRequestService.findAll();
	}
	
	@RequestMapping(value = "/cancel-truck-change-request", method = RequestMethod.POST)
	public void cancelTruckChangeRequest(TruckChangeRequestDto truckChangeRequestDto) {
		 truckChangeRequestService.cancelTruckChangeRequest(truckChangeRequestDto);
	}
}