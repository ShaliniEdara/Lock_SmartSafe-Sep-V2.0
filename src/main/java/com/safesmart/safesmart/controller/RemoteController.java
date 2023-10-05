package com.safesmart.safesmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.SampleTable;
import com.safesmart.safesmart.remoterepository.Remote_KioskRepository;
import com.safesmart.safesmart.remoterepository.Remote_SampleTableRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.SampleRepository;

@RestController
@RequestMapping(value = "/remote")
@CrossOrigin
public class RemoteController {
	
	@Autowired
	private SampleRepository sampleRepository;
	
	@Autowired
	private Remote_SampleTableRepository remote_sampleTableRepository;

	@RequestMapping(value="/sample",method = RequestMethod.POST)
	public void addKishok(@RequestBody SampleTable sampleTable) {
		sampleRepository.save(sampleTable);
	    SampleTable remoteSampleTable = new SampleTable();
	    remoteSampleTable.setName(sampleTable.getName());
	    remoteSampleTable.setAnJi(sampleTable.getAnJi());
		remote_sampleTableRepository.save(remoteSampleTable);
		
	}
}
