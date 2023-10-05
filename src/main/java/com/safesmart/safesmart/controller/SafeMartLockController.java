package com.safesmart.safesmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iicorp.securam.lock.api.LockController;

@RestController
@RequestMapping("/lock")
@CrossOrigin
public class SafeMartLockController {
	
	@Autowired
	private LockController lockController;

	@RequestMapping(value = "/open/{id}")
	public void openLock(@PathVariable("id") String id) throws Exception {
		System.out.println("lockId.."+id);
		lockController.requestOpenLock(Integer.parseInt(id), 10);
	}

}
