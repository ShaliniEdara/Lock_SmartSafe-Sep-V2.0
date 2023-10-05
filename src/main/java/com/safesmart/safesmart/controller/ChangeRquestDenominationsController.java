package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.ChangeValetDenominationsDto;
import com.safesmart.safesmart.service.ChangeRquestDenominationsService;

@RestController
@RequestMapping(value = "/changeRequest")
@CrossOrigin
public class ChangeRquestDenominationsController {
	
	@Autowired
	private ChangeRquestDenominationsService changeRquestDenominationsService;
	
	
	@RequestMapping(value = "/denominations", method = RequestMethod.POST)
	public void createDenominations(@RequestBody ChangeValetDenominationsDto changeValetDenominationsDto) {
		changeRquestDenominationsService.createDenominations(changeValetDenominationsDto);
	}

	@RequestMapping(value = "/denominations/all/{valetDenominationId}", method = RequestMethod.GET)
	public List<ChangeValetDenominationsDto> findAllDenominations(@PathVariable("valetDenominationId") Long valetDenominationId) {
		return changeRquestDenominationsService.findAllDenominations(valetDenominationId);
	}

	

}
