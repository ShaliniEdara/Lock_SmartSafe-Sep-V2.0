package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.KioskRequest;
import com.safesmart.safesmart.dto.LocksRequest;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.service.LocksService;

@RestController
@RequestMapping(value = "/locks")
@CrossOrigin
public class LocksController {

	@Autowired
	private LocksService locksService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody LocksRequest locksRequest) {
		locksRequest.validateRequiredAttributes();
		locksService.add(locksRequest);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<LocksResponse> findAllEmployee() {
		return locksService.findAllUser();
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByLocks(@PathVariable("Id") Long Id) {
		locksService.deleteByLocks(Id);
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public void updateLocks(@PathVariable("Id") Long Id, @RequestBody LocksRequest locksRequest) {
		locksRequest.setId(Id);
		locksService.updateLocks(locksRequest);
	}
	
	@RequestMapping(value = "/unassignedlocks", method = RequestMethod.GET)
	public List<LocksResponse> findUnassignedLocks() {
		return locksService.findUnassignedLocks();
	}
	
}
