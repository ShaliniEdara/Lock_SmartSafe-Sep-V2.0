package com.safesmart.safesmart.service;


//import java.lang.WeakPairMap.Pair.Weak;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.safesmart.safesmart.builder.StoreInfoBuilder;
import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.StoreInfoRequest;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_StoreInfoRepository;
import com.safesmart.safesmart.repository.BillValidatorRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.LocksRepository;
import com.safesmart.safesmart.repository.PrinterRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;

@Service
@Transactional
@CrossOrigin(origins = "*") 
public class StoreInfoService {

	@Autowired
	private  StoreInfoRepository storeInfoRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private KioskRepository kioskRepository;
	
	@Autowired
	private BillValidatorRepository billValidatorRepository;
	
	@Autowired
	private PrinterRepository printerRepository;
	
	@Autowired
	private LocksRepository locksRepository;

	@Autowired
	private StoreInfoBuilder storeInfoBuilder;
	
	@Autowired
	private  Remote_StoreInfoRepository remote_storeInfoRepository;
	

	public StoreInfoResponse getStoreInfoService() {
		StoreInfo storeInfo = storeInfoRepository.findByStoreName("");
		return new StoreInfoResponse(storeInfo.getId(), storeInfo.getStoreName(), storeInfo.getCorpStoreNo(),
				storeInfo.getSerialNumber());
	}

	public StoreInfoResponse getStoreInfoService(String storeName) {
//		StoreInfo storeInfo = storeInfoRepository.findByStoreNameAndConfigured(storeName, true);
		StoreInfo storeInfo = storeInfoRepository.findByStoreName(storeName);

		StoreInfoResponse storeInfoResponse = storeInfoBuilder.toDto(storeInfo);
		return storeInfoResponse;
//		return new StoreInfoResponse(storeInfo.getId(), storeInfo.getStoreName(), storeInfo.getCorpStoreNo(),
//				storeInfo.getSerialNumber());
	}

	public void addStore(StoreInfoRequest storeInfoRequest) {
		StoreInfo storeInfo = storeInfoRepository.findByStoreName(storeInfoRequest.getStoreName());
		if (storeInfo != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS,"StoreName");
		}
		//CorpNo
		StoreInfo storecorp = storeInfoRepository.findByCorpStoreNo(storeInfoRequest.getCorpStoreNo());
		if (storecorp != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS,"CorpStoreNo");	
		}
		//SerialNo
		StoreInfo storeserial = storeInfoRepository.findBySerialNumber(storeInfoRequest.getSerialNumber());
		if (storeserial != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "SerialNumber");
		}
		//AccNo
		StoreInfo storeacc = storeInfoRepository.findByAccountNumber(storeInfoRequest.getAccountNumber());
		if (storeacc != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "AccountNumber");
		}
		
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		storeInfo = storeInfoBuilder.toModel(storeInfoRequest);
		storeInfo.setId(longValue);
		storeInfoRepository.save(storeInfo);
		
		
		//server
		StoreInfo storeInfo1 = remote_storeInfoRepository.findByStoreName(storeInfoRequest.getStoreName());
		if (storeInfo1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS,"StoreName");
		}
		//CorpNo
		StoreInfo storecorp1 = remote_storeInfoRepository.findByCorpStoreNo(storeInfoRequest.getCorpStoreNo());
		if (storecorp1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS,"CorpStoreNo");	
		}
		//SerialNo
		StoreInfo storeserial1 = remote_storeInfoRepository.findBySerialNumber(storeInfoRequest.getSerialNumber());
		if (storeserial1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "SerialNumber");
		}
		//AccNo
		StoreInfo storeacc1 = remote_storeInfoRepository.findByAccountNumber(storeInfoRequest.getAccountNumber());
		if (storeacc1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "AccountNumber");
		}
		
		
		storeInfo1 = storeInfoBuilder.toModel(storeInfoRequest);
		storeInfo1.setId(longValue);
		remote_storeInfoRepository.save(storeInfo1);

	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	
	//sync
//    @Transactional
//    public void syncData() {
//        List<StoreInfo> locals = (List<StoreInfo>) remote_storeInfoRepository.findAll();
//
//        for (StoreInfo local : locals) {
//                 
//        	StoreInfo storeInfo = storeInfoRepository.findByStoreName(local.getStoreName());
//    	
//    		//CorpNo
//        	storeInfo = storeInfoRepository.findByCorpStoreNo(local.getCorpStoreNo());
//    	
//    		//SerialNo
//        	storeInfo = storeInfoRepository.findBySerialNumber(local.getSerialNumber());
//    	
//    		//AccNo
//        	storeInfo= storeInfoRepository.findByAccountNumber(local.getAccountNumber());
//
//    	
//        	
//            if (storeInfo != null) {
//        		System.out.println("3");
//
//            } else {
//        		System.out.println("4");
//        		storeInfo = new StoreInfo();
//        		storeInfo.setAccountNumber(local.getAccountNumber());
//        		storeInfo.setAddress(local.getAddress());
//        		storeInfo.setBankName(local.getBankName());
//        		storeInfo.setConfigured(local.isConfigured());
//        		storeInfo.setStatus(local.isStatus());
//        		storeInfo.setCorpStoreNo(local.getCorpStoreNo());
//        		storeInfo.setMinimumBalance(local.getMinimumBalance());
//        		storeInfo.setSerialNumber(local.getSerialNumber());
//        		storeInfo.setStoreName(local.getStoreName());
//
//        		storeInfo.setStartTime(local.getStartTime());
//        		storeInfo.setEndTime(local.getEndTime());
//       		
//        		storeInfoRepository.save(storeInfo);
//            }
//        }
//    }
//
//    @Scheduled(fixedRate =120000)
//    public void performDataSync() {
//        this.syncData();
//    }	
	

	public List<StoreInfoResponse> findAll() {
		List<StoreInfo> storeInfos = (List<StoreInfo>) storeInfoRepository.findAll();

		return storeInfoBuilder.toDtoList(storeInfos);
	}
	
	public StoreInfoResponse findByStoreName(String storeName) {

		StoreInfo storeInfo = storeInfoRepository.findByStoreName(storeName);
		if (storeInfo == null) {
			
			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, storeName);
         }
		return storeInfoBuilder.toDto(storeInfo);
	}

	public void updateStoreInfo(StoreInfoRequest infoRequest) {

		StoreInfo storeInfo = storeInfoRepository.findByStoreName(infoRequest.getStoreName());

		if (storeInfo != null && storeInfo.getId() != infoRequest.getId()) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, infoRequest.getStoreName());
		}
		
		storeInfo.setStoreName(infoRequest.getStoreName());
		storeInfo.setCorpStoreNo(infoRequest.getCorpStoreNo());
		storeInfo.setSerialNumber(infoRequest.getSerialNumber());
		storeInfo.setAddress(infoRequest.getAddress());
		storeInfo.setBankName(infoRequest.getBankName());
		storeInfo.setAccountNumber(infoRequest.getAccountNumber());
		storeInfo.setMinimumBalance(infoRequest.getMinimumBalance());
		storeInfo.setStoreName(infoRequest.getStoreName());
		storeInfo.setStatus(infoRequest.isStatus());


		
		storeInfoRepository.save(storeInfo);

	}

	
	public void deleteByStoreInfo(Long Id) {
		

		storeInfoRepository.deleteById(Id);
	}
	
	public void configureStore(StoreInfoRequest storeInfoRequest) {
		StoreInfo storeInfo = storeInfoRepository.findById(storeInfoRequest.getId()).get();
		storeInfo.setConfigured(true);
		storeInfoRepository.save(storeInfo);

	}

	
	public void assignStore(Long storeId, Long userId) {
		
		StoreInfo storeInfo = storeInfoRepository.findById(storeId).get();

		Optional<UserInfo> optional = userInfoRepository.findById(userId);
		
		if (optional.isPresent()) {
			UserInfo dbUser = optional.get();
			if (storeInfo != null) {
				dbUser.setStoreInfo(storeInfo);
			} else {
				throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Store");
			}
			
			userInfoRepository.save(dbUser);
		   }	
			else {
			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "User");
		}
	}	
	
//	public void assignStoreKBPL(Long storeId, Long kId, Long bId, Long pId, List<Long> lId) {
//	
//	StoreInfo storeInfo = storeInfoRepository.findById(storeId).get();
//	
//	Optional<Kiosk> optionalk = kioskRepository.findById(kId);
//	
//	Optional<BillValidator> optionalb = billValidatorRepository.findById(bId);
//	
//	Optional<Printer> optionalP = printerRepository.findById(pId);
//	
//	Optional<Locks> optionalL = locksRepository.findById(lId);
//	
//		if (optionalk.isPresent()) {
//			Kiosk dbKiosk = optionalk.get();
//		if (optionalb.isPresent()) {
//			BillValidator dbBill = optionalb.get();
//		if (optionalP.isPresent()) {
//			Printer dbPrinter = optionalP.get();
//		if (optionalL.isPresent()) {
//			Locks dbLocks = optionalL.get();
//		if (storeInfo != null) {
//			dbKiosk.setStoreinfok(storeInfo);
//			dbBill.setStoreinfob(storeInfo);
//			dbPrinter.setStoreinfop(storeInfo);
//			dbLocks.setStoreInfo(storeInfo);
//		} else {
//			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Store");
//		}
//		
//		locksRepository.save(dbLocks);
//		}			
//		else {
//			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Locks");
//		}
//		
//		printerRepository.save(dbPrinter);
//		}
//		else {
//			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Printer");
//		}
//		
//		billValidatorRepository.save(dbBill);
//		}
//		else {
//			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "billValidator");
//		}
//		
//		kioskRepository.save(dbKiosk);
//		}
//		else {
//			throw CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Kiosk");
//		}
//
//     }
	
	public void assignStoreKBPL(Long storeId, Long kId, List<Long> bId, Long pId, List<Long> lId) {

	    StoreInfo storeInfo = storeInfoRepository.findById(storeId).orElseThrow(() -> CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Store"));
	    Kiosk dbKiosk = kioskRepository.findById(kId).orElseThrow(() -> CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Kiosk"));
	    Printer dbPrinter = printerRepository.findById(pId).orElseThrow(() -> CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Printer"));

	    for (Long billId : bId) {
	        BillValidator dbBill = billValidatorRepository.findById(billId).orElseThrow(() -> CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "BillValidator"));

	        dbKiosk.setStoreinfok(storeInfo);
	        dbBill.setStoreinfob(storeInfo);
	        dbPrinter.setStoreinfop(storeInfo);

	        billValidatorRepository.save(dbBill);
	    }

	    for (Long lockId : lId) {
	        Locks dbLocks = locksRepository.findById(lockId).orElseThrow(() -> CommonException.CreateException(CommonExceptionMessage.NOTFOUND, "Locks"));

	        dbKiosk.setStoreinfok(storeInfo);
	        dbPrinter.setStoreinfop(storeInfo);
	        dbLocks.setStoreInfo(storeInfo);

	        locksRepository.save(dbLocks);
	    }

	    kioskRepository.save(dbKiosk);
	    printerRepository.save(dbPrinter);
	}


	public List<StoreInfoResponse> findUnassignedStores() {
		List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
      
		return storeInfoBuilder.toDtoList(storeInfos);
	}
	
	public List<StoreInfoResponse> findUnassignedStoresforUser() {
		List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
		 List<StoreInfoResponse> infoResponses=new ArrayList<StoreInfoResponse>();
		 for (StoreInfo userInfo : storeInfos) {
				if (userInfo.getUsers().isEmpty()){
					
					infoResponses.add(storeInfoBuilder.toDto(userInfo));

				}
		 }
		 return infoResponses;
				
	}
	
	public List<StoreInfoResponse> findUnassignedStoresforKBPL() {
		List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
		 List<StoreInfoResponse> infoResponses=new ArrayList<StoreInfoResponse>();
		 for (StoreInfo userInfo : storeInfos) {
				if (userInfo.getKiosk().isEmpty() && userInfo.getBillValidator().isEmpty() && userInfo.getPrinter().isEmpty()
						&& userInfo.getKiosk().isEmpty()){
					
					infoResponses.add(storeInfoBuilder.toDto(userInfo));

				}
		 }
		 return infoResponses;
				
	}

	public List<StoreInfoResponse> findAssignedStores() {
		List<StoreInfo> storeInfos = storeInfoRepository.findByConfigured(true);
		 List<StoreInfoResponse> infoResponses=new ArrayList<StoreInfoResponse>();
		 for (StoreInfo userInfo : storeInfos) {
				if (userInfo.getUsers().isEmpty() ){
					
				}else {
				infoResponses.add(storeInfoBuilder.toDto(userInfo));
				}
			}
		 return infoResponses;
	}
	
	public List<StoreInfoResponse> findAssignedStoresforreport() {
		List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
		 List<StoreInfoResponse> infoResponses=new ArrayList<StoreInfoResponse>();
		 for (StoreInfo userInfo : storeInfos) {
				if (userInfo.getUsers().isEmpty() ){
					
				}else if(!userInfo.getStoreName().equals("Default_Store")) {
				infoResponses.add(storeInfoBuilder.toDto(userInfo));
				}
			}
		 return infoResponses;
	}
	
    public List<StoreInfoResponse> findAssignedStoresKBPL() {
		
    	List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
    	 List<StoreInfoResponse> infoResponses=new ArrayList<StoreInfoResponse>();
		 for (StoreInfo userInfo : storeInfos) {
				if (userInfo.getKiosk().isEmpty() && userInfo.getBillValidator().isEmpty() && userInfo.getPrinter().isEmpty()
						&& userInfo.getLocks().isEmpty()){
					
				}else {
				infoResponses.add(storeInfoBuilder.toDto(userInfo));
				}
			}
		 return infoResponses;
//    	return storeInfoBuilder.toDtoList(storeInfos);
	}
     public List<StoreInfoResponse> findAssignedStoresUnassignedUsers() {
    	 List<StoreInfo> storeInfos = storeInfoRepository.findByStatus(true);
    	 return storeInfoBuilder.toDtoList(storeInfos);
		
	}

	

	public List<Long> getAllStoreIds() {
		System.out.println("------ we are in getallstoreids method");
		List<Long> storeidsList=storeInfoRepository.getAllStoreIds();
		return storeidsList;
	}

	public StoreInfo findByStoreId(Long id) {
		   System.out.println("We  are in storeInfoService class");
		   Optional<StoreInfo> assignedStoreInfo=storeInfoRepository.findById(id);
		   StoreInfo storeInfo=new StoreInfo();
		    if(assignedStoreInfo.isPresent()) {
		    	storeInfo= assignedStoreInfo.get();
		    }
		    return storeInfo;
	}

	public List<StoreInfoResponse> findByStoresId(Long long1) {
		 Optional<StoreInfo> storeInfoResponses=storeInfoRepository.findById(long1);
		 List<StoreInfoResponse> storeInfoResponses2=new ArrayList<StoreInfoResponse>();
		 if(storeInfoResponses.isPresent()) {
			 storeInfoResponses2=(List<StoreInfoResponse>) storeInfoResponses.get();
		 }
		
		return storeInfoResponses2;
	}

	
	
	
	

}
