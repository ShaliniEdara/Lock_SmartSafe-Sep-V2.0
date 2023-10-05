package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.ValetDenominationsDto;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.service.StandBankService;

@RestController
@RequestMapping(value = "/standbank")
@CrossOrigin
public class StandBankController {

	@Autowired
	private StandBankService standBankService;

	@RequestMapping(value = "/denominations", method = RequestMethod.POST)
	public void createDenominations(@RequestBody ValetDenominationsDto valetDenominationsDto) {
		standBankService.createDenominations(valetDenominationsDto);
	}

	@RequestMapping(value = "/denominations/all", method = RequestMethod.GET)
	public List<ValetDenominations> findAllDenominations() {
		return standBankService.findAllDenominations();
	}

	@RequestMapping(value = "/denominations/{type}", method = RequestMethod.GET)
	public ValetDenominations findDenominations(@PathVariable("type") String type) {
		return standBankService.findDenominations(type);
	}

}
