package com.safesmart.safesmart.controller;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.KioskRequest;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.dto.StoreInfoRequest;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.service.KioskService;

@RestController
@RequestMapping(value = "/kiosk")
@CrossOrigin
public class KioskController {

	@Autowired
	private KioskService kioskService;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody KioskRequest kioskRequest) {
		kioskRequest.validateRequiredAttributes();
		kioskService.add(kioskRequest);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<KioskResponse> findAllEmployee() {
		return kioskService.findAllUser();
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
	public void deleteByKiosk(@PathVariable("Id") Long Id) {
		kioskService.deleteByKiosk(Id);
	}
	
	@RequestMapping(value = "/{Id}", method = RequestMethod.PUT)
	public void updateKiosk(@PathVariable("Id") Long Id, @RequestBody KioskRequest kioskRequest) {
		kioskRequest.setId(Id);
		kioskService.updateKiosk(kioskRequest);
	}
	
	@RequestMapping(value = "/unassignedKiosks", method = RequestMethod.GET)
	public List<KioskResponse> findUnassignedKiosk() {
		return kioskService.findUnassignedKiosk();
	}
	
	@RequestMapping(value = "/ip", method = RequestMethod.GET)
	public String ipss() {		
		return kioskService.ipss();
	}
	
	@RequestMapping(value = "/{storeName}", method = RequestMethod.GET)
	public StoreInfoResponse updateStoreInfo(@PathVariable("storeName") String storeName) {
		return kioskService.kioskInfo(storeName);

	}
	
	@RequestMapping(value = "/macInfo", method = RequestMethod.GET)
	public List<KioskResponse> KioskInfosys() throws SocketException, UnknownHostException {
		return kioskService.KioskInfosys();
	}
	
	@RequestMapping(value = "/assign/store/{storeId}/kiosk/{kioskId}", method = RequestMethod.GET)
	public void assignStoretokiosk(@PathVariable(value = "storeId") Long storeId,
			@PathVariable(value = "kioskId") Long kioskId) {

		kioskService.assignStoretokiosk(storeId, kioskId);
	}
	
}
