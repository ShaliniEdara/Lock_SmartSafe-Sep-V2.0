package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safesmart.safesmart.dto.ValetDenominationsDto;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_ValetDenominationsRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;

@Service
public class StandBankService {

	@Autowired
	private ValetDenominationsRepository valetDenominationsRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private Remote_ValetDenominationsRepository remote_valetDenominationsRepository;
	
	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	public void createDenominations(ValetDenominationsDto valetDenominationsDto) {
		ValetDenominations dbvaletDenominations = valetDenominationsRepository.findByType(valetDenominationsDto.getType());
		ValetDenominations valetDenominations = convertToModel(valetDenominationsDto);
		Optional<UserInfo> optional = userInfoRepository.findById(valetDenominationsDto.getUpdatedByUser());
		
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		if (dbvaletDenominations == null) {
			valetDenominations.setId(longValue);
			if (optional.isPresent()) {
				valetDenominations.setCreatedBy(optional.get());
				valetDenominations.setCreated(LocalDateTime.now());
			}
			
		} else {
			if (optional.isPresent()) {
				valetDenominations.setModifiedBy(optional.get());
				valetDenominations.setModified(LocalDateTime.now());
			}
			//throw new RuntimeException("Denomination already created.");
		}
		valetDenominationsRepository.save(valetDenominations);
		
		//server
//		ValetDenominations dbvaletDenominations1 = remote_valetDenominationsRepository.findByType(valetDenominationsDto.getType());
//		ValetDenominations valetDenominations1 = convertToModel(valetDenominationsDto);
//		Optional<UserInfo> optional1 = remote_userInfoRepository.findById(valetDenominationsDto.getUpdatedByUser());
//		
//		if (dbvaletDenominations1 == null) {
//			valetDenominations1.setId(longValue);
//			if (optional1.isPresent()) {
//				valetDenominations1.setCreatedBy(optional1.get());
//				valetDenominations1.setCreated(LocalDateTime.now());
//			}
//			
//		} else {
//			if (optional.isPresent()) {
//				valetDenominations1.setModifiedBy(optional1.get());
//				valetDenominations1.setModified(LocalDateTime.now());
//			}
//		}
//		remote_valetDenominationsRepository.save(valetDenominations1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;
	    }

	private ValetDenominations convertToModel(ValetDenominationsDto valetDenominationsDto) {
		ValetDenominations valetDenominations = new ValetDenominations();
		BeanUtils.copyProperties(valetDenominationsDto, valetDenominations);
		return valetDenominations;
	}

	public List<ValetDenominations> findAllDenominations() {
		return (List<ValetDenominations>) valetDenominationsRepository.findAll();
	}

	public ValetDenominations findDenominations(String type) {
		return valetDenominationsRepository.findByType(type);
	}

}
