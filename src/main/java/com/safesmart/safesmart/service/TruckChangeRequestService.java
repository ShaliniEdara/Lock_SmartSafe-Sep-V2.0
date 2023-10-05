package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safesmart.safesmart.dto.TruckChangeRequestDto;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.TruckChangeRequest;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_ChangeRequestRepository;
import com.safesmart.safesmart.remoterepository.Remote_TruckChangeRequestRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_ValetDenominationsRepository;
import com.safesmart.safesmart.repository.ChangeRequestRepository;
import com.safesmart.safesmart.repository.TruckChangeRequestRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;
import com.safesmart.safesmart.util.EmailTemplate;

@Service
public class TruckChangeRequestService {

	@Autowired
	private TruckChangeRequestRepository truckChangeRequestRepository;
	
	@Autowired
	private ChangeRequestRepository changeRequestRepository;
	
	@Autowired
	private ValetDenominationsRepository valetDenominationsRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private Remote_TruckChangeRequestRepository remote_truckChangeRequestRepository;
	
	@Autowired
	private Remote_ChangeRequestRepository remote_changeRequestRepository;
	

	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;
	
	@Autowired
	private EmailTemplate emailTemplate;
	
	public void createTruckChangeRequest(TruckChangeRequestDto truckChangeRequestDto) {
		TruckChangeRequest truckChangeRequest = new TruckChangeRequest();
		BeanUtils.copyProperties(truckChangeRequestDto, truckChangeRequest);
		Optional<UserInfo> optional = userInfoRepository.findById(truckChangeRequestDto.getUpdatedByUser());
		
		UUID uuid = UUID.randomUUID();
		Long longValue = uuidToLong(uuid);
		
		if (optional.isPresent()) {
			truckChangeRequest.setId(longValue);
			truckChangeRequest.setCreatedBy(optional.get());
			truckChangeRequest.setCreated(LocalDateTime.now());
		}
		//ValetDenominations dbValetDenominations = valetDenominationsRepository.findByType(truckChangeRequestDto.getType());
		// add valetDenomination to truckChange 
		
		
		//updateValetWithTruckchangeRequest(truckChangeRequestDto, dbValetDenominations);
		//dbValetDenominations.setModifiedBy(optional.get());
		//dbValetDenominations.setModified(LocalDateTime.now());
		//valetDenominationsRepository.save(dbValetDenominations);
		String status="";
		if(truckChangeRequestDto.getOrderStatus().toString()=="Ordered") {
			status="Pending";
		}
		else if(truckChangeRequestDto.getOrderStatus().toString()=="Delivered") {
			status="Ordered";
		}
		ChangeRequest changeRequest = changeRequestRepository.findByTypeAndOrderStatus(truckChangeRequestDto.getType(), status);
		changeRequest.setOrderStatus(truckChangeRequestDto.getOrderStatus().toString());
		changeRequestRepository.save(changeRequest);
		truckChangeRequestRepository.save(truckChangeRequest);
		emailTemplate.confirmTruckchangeMail(truckChangeRequest);
		
		//server
//		TruckChangeRequest truckChangeRequest1 = new TruckChangeRequest();
//		BeanUtils.copyProperties(truckChangeRequestDto, truckChangeRequest1);
//		Optional<UserInfo> optional1 = remote_userInfoRepository.findById(truckChangeRequestDto.getUpdatedByUser());
//		if (optional1.isPresent()) {
//			truckChangeRequest1.setId(longValue);
//			truckChangeRequest1.setCreatedBy(optional1.get());
//			truckChangeRequest1.setCreated(LocalDateTime.now());
//		}
//
//		String status1="";
//		if(truckChangeRequestDto.getOrderStatus().toString()=="Ordered") {
//			status1="Pending";
//		}
//		else if(truckChangeRequestDto.getOrderStatus().toString()=="Delivered") {
//			status1="Ordered";
//		}
//		ChangeRequest changeRequest1 = remote_changeRequestRepository.findByTypeAndOrderStatus(truckChangeRequestDto.getType(), status1);
//		changeRequest1.setOrderStatus(truckChangeRequestDto.getOrderStatus().toString());
//		remote_changeRequestRepository.save(changeRequest1);
//		remote_truckChangeRequestRepository.save(truckChangeRequest1);
//		emailTemplate.confirmTruckchangeMail(truckChangeRequest1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }

	public List<TruckChangeRequest> findAll() {
		return truckChangeRequestRepository.findAll();
	}
	public void cancelTruckChangeRequest(TruckChangeRequestDto truckChangeRequestDto) {
		TruckChangeRequest truckChangeRequest = new TruckChangeRequest();
		BeanUtils.copyProperties(truckChangeRequestDto, truckChangeRequest);
		Optional<UserInfo> optional = userInfoRepository.findById(truckChangeRequestDto.getUpdatedByUser());
		if (optional.isPresent()) {
			truckChangeRequest.setCreatedBy(optional.get());
			truckChangeRequest.setCreated(LocalDateTime.now());
		}
		//ValetDenominations dbValetDenominations = valetDenominationsRepository.findByType(truckChangeRequestDto.getType());
		// add valetDenomination to truckChange 
		
		
		//updateValetWithTruckchangeRequest(truckChangeRequestDto, dbValetDenominations);
		//dbValetDenominations.setModifiedBy(optional.get());
		//dbValetDenominations.setModified(LocalDateTime.now());
		//valetDenominationsRepository.save(dbValetDenominations);
		
		ChangeRequest changeRequest = changeRequestRepository.findByTypeAndOrderStatus(truckChangeRequestDto.getType(), "Ordered");
		changeRequest.setOrderStatus("Cancelled");
		changeRequestRepository.save(changeRequest);
		truckChangeRequestRepository.save(truckChangeRequest);
		emailTemplate.cancelTruckchangeMail(truckChangeRequest);
	}

}