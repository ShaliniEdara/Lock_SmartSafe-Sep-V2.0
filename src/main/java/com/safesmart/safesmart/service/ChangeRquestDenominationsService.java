package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safesmart.safesmart.builder.ChangeRquestBuilder;
import com.safesmart.safesmart.dto.ChangeValetDenominationsDto;
import com.safesmart.safesmart.model.ChangeValetDenominations;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.remoterepository.Remote_ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_ValetDenominationsRepository;
import com.safesmart.safesmart.repository.ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;

@Service
public class ChangeRquestDenominationsService {

	@Autowired
	private ChangeRquestDenominationsRepository changeRquestDenominationsRepository;

	@Autowired
	private ChangeRquestBuilder changeRquestBuilder;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private ValetDenominationsRepository valetDenominationsRepository;
	
	@Autowired
	private Remote_ChangeRquestDenominationsRepository remote_changeRquestDenominationsRepository;

	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	@Autowired
	private Remote_ValetDenominationsRepository remote_valetDenominationsRepository;

	public void createDenominations(ChangeValetDenominationsDto changeValetDenominationsDto) {

		ChangeValetDenominations changeValetDenominations = changeRquestBuilder
				.convertToModel(changeValetDenominationsDto);

		changeValetDenominations.setUpdatedOn(LocalDateTime.now());
		Optional<UserInfo> optional = userInfoRepository.findById(changeValetDenominationsDto.getUpdatedByUser());

		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		if (optional.isPresent()) {
			changeValetDenominations.setId(longValue);
			changeValetDenominations.setCreatedBy(optional.get());
			changeValetDenominations.setCreated(LocalDateTime.now());
		}

		Optional<ValetDenominations> optional2 = valetDenominationsRepository
				.findById(changeValetDenominationsDto.getValetDenominationsId());
		if (optional2.isPresent()) {
			ValetDenominations valetDenominations = optional2.get();
			valetDenominations.setCents(changeValetDenominationsDto.getValetDenominationsDto().getCents());
			valetDenominations.setDen_1$(changeValetDenominationsDto.getValetDenominationsDto().getDen_1$());
			valetDenominations.setDen_10$(changeValetDenominationsDto.getValetDenominationsDto().getDen_10$());
			valetDenominations.setDen_100$(changeValetDenominationsDto.getValetDenominationsDto().getDen_100$());
			valetDenominations.setDen_20$(changeValetDenominationsDto.getValetDenominationsDto().getDen_20$());
			valetDenominations.setDen_5$(changeValetDenominationsDto.getValetDenominationsDto().getDen_5$());
			valetDenominations.setDen_50$(changeValetDenominationsDto.getValetDenominationsDto().getDen_50$());
			valetDenominations.setDimes(changeValetDenominationsDto.getValetDenominationsDto().getDimes());
			valetDenominations.setNickels(changeValetDenominationsDto.getValetDenominationsDto().getNickels());
			valetDenominations.setQuarters(changeValetDenominationsDto.getValetDenominationsDto().getQuarters());
			valetDenominations.setModified(LocalDateTime.now());
			valetDenominations.setModifiedBy(optional.get());
			valetDenominationsRepository.save(valetDenominations);
			changeValetDenominations.setValetDenominations(valetDenominations);
		}
		//changeValetDenominations.setCreatedBy(changeValetDenominationsDto.getUpdatedByUser());
		changeRquestDenominationsRepository.save(changeValetDenominations);
		
		
		//server
//		ChangeValetDenominations changeValetDenominations1 = changeRquestBuilder
//				.convertToModel(changeValetDenominationsDto);
//
//		changeValetDenominations1.setUpdatedOn(LocalDateTime.now());
//		Optional<UserInfo> optional1 = remote_userInfoRepository.findById(changeValetDenominationsDto.getUpdatedByUser());
//
//		if (optional1.isPresent()) {
//			changeValetDenominations1.setId(longValue);
//			changeValetDenominations1.setCreatedBy(optional1.get());
//			changeValetDenominations1.setCreated(LocalDateTime.now());
//		}
//
//		Optional<ValetDenominations> optional3 = remote_valetDenominationsRepository
//				.findById(changeValetDenominationsDto.getValetDenominationsId());
//		if (optional3.isPresent()) {
//			ValetDenominations valetDenominations1 = optional3.get();
//			valetDenominations1.setCents(changeValetDenominationsDto.getValetDenominationsDto().getCents());
//			valetDenominations1.setDen_1$(changeValetDenominationsDto.getValetDenominationsDto().getDen_1$());
//			valetDenominations1.setDen_10$(changeValetDenominationsDto.getValetDenominationsDto().getDen_10$());
//			valetDenominations1.setDen_100$(changeValetDenominationsDto.getValetDenominationsDto().getDen_100$());
//			valetDenominations1.setDen_20$(changeValetDenominationsDto.getValetDenominationsDto().getDen_20$());
//			valetDenominations1.setDen_5$(changeValetDenominationsDto.getValetDenominationsDto().getDen_5$());
//			valetDenominations1.setDen_50$(changeValetDenominationsDto.getValetDenominationsDto().getDen_50$());
//			valetDenominations1.setDimes(changeValetDenominationsDto.getValetDenominationsDto().getDimes());
//			valetDenominations1.setNickels(changeValetDenominationsDto.getValetDenominationsDto().getNickels());
//			valetDenominations1.setQuarters(changeValetDenominationsDto.getValetDenominationsDto().getQuarters());
//			valetDenominations1.setModified(LocalDateTime.now());
//			valetDenominations1.setModifiedBy(optional1.get());
//			remote_valetDenominationsRepository.save(valetDenominations1);
//			changeValetDenominations1.setValetDenominations(valetDenominations1);
//		}
//		remote_changeRquestDenominationsRepository.save(changeValetDenominations1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	

	public List<ChangeValetDenominationsDto> findAllDenominations(Long valetDenominationId) {

		List<ChangeValetDenominationsDto> response = new ArrayList<ChangeValetDenominationsDto>();

		List<ChangeValetDenominations> list = changeRquestDenominationsRepository
				.findByValetDenominations_Id(valetDenominationId);
		if (list != null && list.size() > 0) {
			response = changeRquestBuilder.convertToDtosList(list);
		}

		return response;
	}

}
