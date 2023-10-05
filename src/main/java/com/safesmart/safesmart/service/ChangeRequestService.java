package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safesmart.safesmart.dto.ChangeRequestDto;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.OrderStatus;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_ChangeRequestRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.repository.ChangeRequestRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.util.EmailTemplate;

@Service
public class ChangeRequestService {

	@Autowired
	private ChangeRequestRepository changeRequestRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private Remote_ChangeRequestRepository remote_changeRequestRepository;

	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	@Autowired
	private EmailTemplate emailTemplate;
	
	public void createChangeRequest(ChangeRequestDto changeRequestDto) {
		ChangeRequest changeRequest = new ChangeRequest();
		BeanUtils.copyProperties(changeRequestDto, changeRequest);
		Optional<UserInfo> optional = userInfoRepository.findById(changeRequestDto.getUpdatedByUser());
		
		UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		 
		if (optional.isPresent()) {
			changeRequest.setId(longValue);
			changeRequest.setCreatedBy(optional.get());
			changeRequest.setCreated(LocalDateTime.now());
		}
		changeRequest.setOrderStatus(changeRequestDto.getOrderStatus());
		changeRequestRepository.save(changeRequest);
		emailTemplate.createChangeRequestMail(changeRequestDto);
		//send mail to truck owner
		
		//server
//		ChangeRequest changeRequest1 = new ChangeRequest();
//		BeanUtils.copyProperties(changeRequestDto, changeRequest1);
//		Optional<UserInfo> optional1 = remote_userInfoRepository.findById(changeRequestDto.getUpdatedByUser());
//		if (optional1.isPresent()) {
//			changeRequest1.setId(longValue);
//			changeRequest1.setCreatedBy(optional1.get());
//			changeRequest1.setCreated(LocalDateTime.now());
//		}
//		changeRequest1.setOrderStatus(changeRequestDto.getOrderStatus());
//		remote_changeRequestRepository.save(changeRequest1);
//		emailTemplate.createChangeRequestMail(changeRequestDto);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }

	public List<ChangeRequest> findAll() {
		return changeRequestRepository.findAll();
	}
	
	public ChangeRequest findByTypeAndOrderStatus(String type, String orderStatus) {
		return  changeRequestRepository.findByTypeAndOrderStatus(type, orderStatus);
	}

}
