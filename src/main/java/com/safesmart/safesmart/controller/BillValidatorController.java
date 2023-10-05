package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.BillValidatorRequest;
import com.safesmart.safesmart.dto.BillValidatorResponse;
import com.safesmart.safesmart.dto.KioskRequest;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.service.BillValidatorService;

@RestController
@RequestMapping(value = "/billValidator")
@CrossOrigin
public class BillValidatorController {

	@Autowired
	private BillValidatorService billValidatorService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody BillValidatorRequest billValidatorRequest) {
		billValidatorRequest.validateRequiredAttributes();
		billValidatorService.add(billValidatorRequest);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<BillValidatorResponse> findAllEmployee() {
		return billValidatorService.findAllUser();
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByBillValidator(@PathVariable("Id") Long Id) {
		billValidatorService.deleteByBillValidator(Id);
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public void updateBillValidator(@PathVariable("Id") Long Id, @RequestBody BillValidatorRequest billValidatorRequest) {
		billValidatorRequest.setId(Id);
		billValidatorService.updateBillValidator(billValidatorRequest);
	}
	
	@RequestMapping(value = "/unassignedBillValidators", method = RequestMethod.GET)
	public List<BillValidatorResponse> findUnassignedBillValidator() {
		return billValidatorService.findUnassignedBillValidator();
	}
	
}
