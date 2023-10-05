package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.BillValidatorResponse;
import com.safesmart.safesmart.dto.LocksRequest;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.PrinterRequest;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.service.PrinterService;

@RestController
@RequestMapping(value = "/printer")
@CrossOrigin
public class PrinterController {

	
	@Autowired
	private PrinterService printerService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody PrinterRequest printerRequest) {
		printerRequest.validateRequiredAttributes();
		printerService.add(printerRequest);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<PrinterResponse> findAllEmployee() {
		return printerService.findAllUser();
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByPrinter(@PathVariable("Id") Long Id) {
		printerService.deleteByPrinter(Id);
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public void updatePrinter(@PathVariable("Id") Long Id, @RequestBody PrinterRequest printerRequest) {
		printerRequest.setId(Id);
		printerService.updatePrinter(printerRequest);
	}
	
	@RequestMapping(value = "/unassignedPrinters", method = RequestMethod.GET)
	public List<PrinterResponse> findUnassignedPrinters() {
		return printerService.findUnassignedPrinters();
	}
	
}
