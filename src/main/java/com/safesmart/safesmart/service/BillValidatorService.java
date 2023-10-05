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
import com.safesmart.safesmart.dto.KioskRequest;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.remoterepository.Remote_BillValidatorRepository;
import com.safesmart.safesmart.repository.BillValidatorRepository;

@Service
@Transactional
public class BillValidatorService {

	@Autowired
	private BillValidatorRepository billValidatorRepository;
	
	@Autowired
	private Remote_BillValidatorRepository remote_billValidatorRepository;
	
	public void add(BillValidatorRequest billValidatorRequest) {

		BillValidator billValidator = billValidatorRepository.findByBillAcceptorName(billValidatorRequest.getBillAcceptorName());
		if (billValidator != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "BillAcceptorName");
		}
		
		//validation for billvalidatorNo
		BillValidator billValidatorNo = billValidatorRepository.findByBillAcceptorNo(billValidatorRequest.getBillAcceptorNo());
		if (billValidatorNo != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "BillAcceptorNo");
		}
	
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);

		billValidator = new BillValidator();
		billValidator.setId(longValue);
		billValidator.setBillAcceptorNo(billValidatorRequest.getBillAcceptorNo());
		billValidator.setBillAcceptorName(billValidatorRequest.getBillAcceptorName());
		billValidator.setBrandName(billValidatorRequest.getBrandName());
		billValidator.setModelName(billValidatorRequest.getModelName());
		billValidator.setMachineType(billValidatorRequest.getMachineType());
		billValidator.setStorageCapacity(billValidatorRequest.getStorageCapacity());
	
		billValidator.setActive(billValidatorRequest.isActive());

	
		billValidatorRepository.save(billValidator);
		
		//server
		BillValidator billValidator1 = remote_billValidatorRepository.findByBillAcceptorName(billValidatorRequest.getBillAcceptorName());
		if (billValidator1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "BillAcceptorName");
		}
		
		//validation for billvalidatorNo
		BillValidator billValidatorNo1 = remote_billValidatorRepository.findByBillAcceptorNo(billValidatorRequest.getBillAcceptorNo());
		if (billValidatorNo1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "BillAcceptorNo");
		}
	

		billValidator1 = new BillValidator();
		billValidator1.setId(longValue);
		billValidator1.setBillAcceptorNo(billValidatorRequest.getBillAcceptorNo());
		billValidator1.setBillAcceptorName(billValidatorRequest.getBillAcceptorName());
		billValidator1.setBrandName(billValidatorRequest.getBrandName());
		billValidator1.setModelName(billValidatorRequest.getModelName());
		billValidator1.setMachineType(billValidatorRequest.getMachineType());
		billValidator1.setStorageCapacity(billValidatorRequest.getStorageCapacity());
	
		billValidator1.setActive(billValidatorRequest.isActive());

	
		remote_billValidatorRepository.save(billValidator1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	public List<BillValidatorResponse> findAllUser() {
		// TODO Auto-generated method stub
		List<BillValidator> billValidators = (List<BillValidator>) billValidatorRepository.findAll();

		List<BillValidatorResponse> billValidatorResponses = new ArrayList<BillValidatorResponse>();

		for (BillValidator billValidator : billValidators) {
			billValidatorResponses.add(new BillValidatorResponse(billValidator.getId(),billValidator.getBillAcceptorNo(), billValidator.getBillAcceptorName(), billValidator.getBrandName(),
					billValidator.getModelName(), billValidator.getMachineType(), billValidator.getStorageCapacity(), billValidator.isActive()));
		}
		return billValidatorResponses;
	}
	
	public void deleteByBillValidator(Long Id) {
		billValidatorRepository.deleteById(Id);
	}
	
	public void updateBillValidator(BillValidatorRequest billValidatorRequest) {


		BillValidator billValidator = billValidatorRepository.findById(billValidatorRequest.getId()).get();

		billValidator.setBillAcceptorNo(billValidatorRequest.getBillAcceptorNo());
		billValidator.setBillAcceptorName(billValidatorRequest.getBillAcceptorName());
		billValidator.setBrandName(billValidatorRequest.getBrandName());
		billValidator.setModelName(billValidatorRequest.getModelName());
		billValidator.setMachineType(billValidatorRequest.getMachineType());
		billValidator.setStorageCapacity(billValidatorRequest.getStorageCapacity());
	
		billValidator.setActive(billValidatorRequest.isActive());
		
		billValidatorRepository.save(billValidator);

	}
	
	public List<BillValidatorResponse> findUnassignedBillValidator() {
		List<BillValidator> billValidators = (List<BillValidator>) billValidatorRepository.findByActive(true);
		List<BillValidatorResponse> infoResponses = new ArrayList<BillValidatorResponse>();
		for (BillValidator billValidator : billValidators) {
			if (billValidator != null && billValidator.getStoreinfob() == null) {
				infoResponses.add(new BillValidatorResponse(billValidator.getId(),billValidator.getBillAcceptorNo(), billValidator.getBillAcceptorName(), billValidator.getBrandName(),
						billValidator.getModelName(), billValidator.getMachineType(), billValidator.getStorageCapacity(), billValidator.isActive()));
			}
		}
		return infoResponses;
	}

	public BillValidator getBillValidatorInfo(Long billid) {
		Optional<BillValidator> billValidator = billValidatorRepository.findById(billid);
		BillValidator billValidatorInfo = new BillValidator();
		if(billValidator.isPresent()) {
			billValidatorInfo=billValidator.get();
		}
		return billValidatorInfo;
	}
	
}
