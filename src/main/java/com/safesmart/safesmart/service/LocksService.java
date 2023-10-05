package com.safesmart.safesmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.BillValidatorRequest;
import com.safesmart.safesmart.dto.BillValidatorResponse;
import com.safesmart.safesmart.dto.LocksRequest;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.RolesDto;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_LocksRepository;
import com.safesmart.safesmart.repository.LocksRepository;

@Service
@Transactional
public class LocksService {

	
	@Autowired
	private LocksRepository locksRepository;
	@Autowired
	private Remote_LocksRepository remote_locksRepository;
	
	public void add(LocksRequest locksRequest) {

		Locks locks = locksRepository.findByDigitalLockName(locksRequest.getDigitalLockName());
		if (locks != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "DigitalLockName");
		}
		
		//validation for lockNo
		Locks locksNo = locksRepository.findByDigitalLockNo(locksRequest.getDigitalLockNo());
		if (locksNo != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "DigitalLockNo");
		}
	
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		locks = new Locks();
		locks.setId(longValue);
		locks.setDigitalLockNo(locksRequest.getDigitalLockNo());
		locks.setDigitalLockName(locksRequest.getDigitalLockName());
		locks.setBrandName(locksRequest.getBrandName());
		locks.setModelName(locksRequest.getModelName());
		locks.setMachineType(locksRequest.getMachineType());
		locks.setConnectors(locksRequest.getConnectors());
	
		locks.setActive(locksRequest.isActive());

		locksRepository.save(locks);
		
		//server
		Locks locks1 = remote_locksRepository.findByDigitalLockName(locksRequest.getDigitalLockName());
		if (locks1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "DigitalLockName");
		}
		
		//validation for lockNo
		Locks locksNo1 = remote_locksRepository.findByDigitalLockNo(locksRequest.getDigitalLockNo());
		if (locksNo1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "DigitalLockNo");
		}
	

		locks1 = new Locks();
		locks1.setId(longValue);
		locks1.setDigitalLockNo(locksRequest.getDigitalLockNo());
		locks1.setDigitalLockName(locksRequest.getDigitalLockName());
		locks1.setBrandName(locksRequest.getBrandName());
		locks1.setModelName(locksRequest.getModelName());
		locks1.setMachineType(locksRequest.getMachineType());
		locks1.setConnectors(locksRequest.getConnectors());
	
		locks1.setActive(locksRequest.isActive());

		remote_locksRepository.save(locks1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	public List<LocksResponse> findAllUser() {
		// TODO Auto-generated method stub
		List<Locks> lockss = (List<Locks>) locksRepository.findAll();

		List<LocksResponse> locksResponses = new ArrayList<LocksResponse>();

		for (Locks locks : lockss) {
			locksResponses.add(new LocksResponse(locks.getId(),locks.getDigitalLockNo(), locks.getDigitalLockName(), locks.getBrandName(),
				locks.getModelName(), locks.getMachineType(), locks.getConnectors(), locks.isActive()));
		}
		return locksResponses;
	}
	
	public void deleteByLocks(Long Id) {	
		locksRepository.deleteById(Id);
	}

			
	
	public void updateLocks(LocksRequest locksRequest) {


		Locks locks = locksRepository.findById(locksRequest.getId()).get();

		locks.setDigitalLockNo(locksRequest.getDigitalLockNo());
		locks.setDigitalLockName(locksRequest.getDigitalLockName());
		locks.setBrandName(locksRequest.getBrandName());
		locks.setModelName(locksRequest.getModelName());
		locks.setMachineType(locksRequest.getMachineType());
		locks.setConnectors(locksRequest.getConnectors());
	
		locks.setActive(locksRequest.isActive());

	
		locksRepository.save(locks);

	}
	
	public List<LocksResponse> findUnassignedLocks() {
		List<Locks> lockss = (List<Locks>) locksRepository.findByActive(true);
		List<LocksResponse> infoResponses = new ArrayList<LocksResponse>();
		for (Locks locks : lockss) {
			if (locks != null && locks.getStoreInfo() == null) {
				infoResponses.add(new LocksResponse(locks.getId(),locks.getDigitalLockNo(), locks.getDigitalLockName(), locks.getBrandName(),
						locks.getModelName(), locks.getMachineType(), locks.getConnectors(), locks.isActive()));
			}
		}
		return infoResponses;
	}

	public Locks getLocksInfo(Long locksid) {
	      Optional<Locks> locks=locksRepository.findById(locksid);
	      Locks locksInfo=new Locks();
	      if(locks.isPresent()) {
	    	  locksInfo=locks.get();
	      }
		return locksInfo;
	}
	
}
