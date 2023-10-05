package com.safesmart.safesmart.builder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safesmart.safesmart.dto.StoreInfoRequest;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;

@Component
public class StoreInfoBuilder {

	public StoreInfo toModel(StoreInfoRequest storeInfoRequest) {

		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setId(storeInfoRequest.getId());
		storeInfo.setAccountNumber(storeInfoRequest.getAccountNumber());
		storeInfo.setAddress(storeInfoRequest.getAddress());
		storeInfo.setBankName(storeInfoRequest.getBankName());
		storeInfo.setConfigured(storeInfoRequest.isConfigured());
		storeInfo.setStatus(storeInfoRequest.isStatus());
		storeInfo.setCorpStoreNo(storeInfoRequest.getCorpStoreNo());
		storeInfo.setMinimumBalance(storeInfoRequest.getMinimumBalance());
		storeInfo.setSerialNumber(storeInfoRequest.getSerialNumber());
		storeInfo.setStoreName(storeInfoRequest.getStoreName());

		storeInfo.setStartTime(LocalTime.parse(storeInfoRequest.getStartTime()));
		storeInfo.setEndTime(LocalTime.parse(storeInfoRequest.getEndTime()));
		storeInfo.setStreetName(storeInfoRequest.getStreetName());
		storeInfo.setCityName(storeInfoRequest.getCityName());
		storeInfo.setStateName(storeInfoRequest.getStateName());
		storeInfo.setZipCode(storeInfoRequest.getZipCode());
		storeInfo.setCropName(storeInfoRequest.getCropName());
		
		return storeInfo;
	}

	public StoreInfoResponse toDto(StoreInfo storeInfo) {
		StoreInfoResponse storeInfoResponse = new StoreInfoResponse();
		storeInfoResponse.setAccountNumber(storeInfo.getAccountNumber());
		storeInfoResponse.setAddress(storeInfo.getAddress());
		storeInfoResponse.setBankName(storeInfo.getBankName());
		storeInfoResponse.setConfigured(storeInfo.isConfigured());
		storeInfoResponse.setStatus(storeInfo.isStatus());
		storeInfoResponse.setCorpStoreNo(storeInfo.getCorpStoreNo());
		storeInfoResponse.setId(storeInfo.getId());
		storeInfoResponse.setMinimumBalance(storeInfo.getMinimumBalance());
		storeInfoResponse.setSerialNumber(storeInfo.getSerialNumber());
		storeInfoResponse.setStoreName(storeInfo.getStoreName());
		storeInfoResponse.setStartTime(storeInfo.getStartTime().toString());
		storeInfoResponse.setEndTime(storeInfo.getEndTime().toString());
		storeInfoResponse.setStreetName(storeInfo.getStreetName());
		storeInfoResponse.setCityName(storeInfo.getCityName());
		storeInfoResponse.setStateName(storeInfo.getStateName());
		storeInfoResponse.setZipCode(storeInfo.getZipCode());
		storeInfoResponse.setCropName(storeInfo.getCropName());
		

		List<Long> userIds = new ArrayList<Long>();
		for (UserInfo user : storeInfo.getUsers()) {
			userIds.add(user.getId());
		}
		storeInfoResponse.setUserIds(userIds);
		
		List<Long> kIds = new ArrayList<Long>();
		for (Kiosk kiosk : storeInfo.getKiosk()) {
			kIds.add(kiosk.getId());
		}
		storeInfoResponse.setKioskIds(kIds);
		
		List<Long> bIds = new ArrayList<Long>();
		for (BillValidator billValidator : storeInfo.getBillValidator()) {
			bIds.add(billValidator.getId());
		}
		storeInfoResponse.setBillValidatorIds(bIds);
		
		List<Long> lIds = new ArrayList<Long>();
		for (Locks lock : storeInfo.getLocks()) {
			lIds.add(lock.getId());
		}
		storeInfoResponse.setLockIds(lIds);
		
		List<Long> pIds = new ArrayList<Long>();
		for (Printer printer : storeInfo.getPrinter()) {
			pIds.add(printer.getId());
		}
		storeInfoResponse.setPrinterIds(pIds);
		
		return storeInfoResponse;

	}

	public List<StoreInfoResponse> toDtoList(List<StoreInfo> storeInfos) {

		List<StoreInfoResponse> infoResponses = new ArrayList<StoreInfoResponse>();

		for (StoreInfo storeInfo : storeInfos) {
			infoResponses.add(toDto(storeInfo));
		}

		return infoResponses;
	}
}
