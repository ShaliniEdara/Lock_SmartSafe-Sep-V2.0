package com.safesmart.safesmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.service.LockInfoService;

@RestController
@RequestMapping(value = "/locksInfo")
@CrossOrigin
public class LockInfoController {

	@Autowired
	private LockInfoService lockInfoService;
	
	
	@RequestMapping(value = "/{userId}/{type}", method = RequestMethod.GET)
	public void add(@PathVariable("userId") Long userId, @PathVariable("type") String type) {
		System.out.println("coming lock info");
		lockInfoService.add(userId,type);
	}
}
