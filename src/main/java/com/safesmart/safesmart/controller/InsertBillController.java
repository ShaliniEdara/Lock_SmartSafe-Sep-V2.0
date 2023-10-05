package com.safesmart.safesmart.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.InsertBillRequest;
import com.safesmart.safesmart.dto.InsertBillResponse;
import com.safesmart.safesmart.service.InsertBillService;

@RestController
@RequestMapping("/insertbill")
@CrossOrigin
public class InsertBillController {

	@Autowired
	private InsertBillService insertbillService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody InsertBillRequest insertBillRequest) {
	    insertBillRequest.dollerValidation();
		insertbillService.add(insertBillRequest);
	}

	@RequestMapping(value = "/incomplete", method = RequestMethod.GET)
	public Collection<InsertBillResponse> getIncompleteInsertBills() {
		return insertbillService.getIncompleteInsertBills();
	}

	@RequestMapping(value = "/processBills/{userId}", method = RequestMethod.POST)
	public InsertBillResponse processInsertBills(@PathVariable("userId") Long userId) {
		return insertbillService.processInsertBills(userId);

	}

}
