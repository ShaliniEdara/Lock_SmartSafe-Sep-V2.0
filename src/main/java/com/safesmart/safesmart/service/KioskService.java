package com.safesmart.safesmart.service;



import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.builder.StoreInfoBuilder;
import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.KioskRequest;
import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.Kiosk;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Role;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_KioskRepository;
import com.safesmart.safesmart.repository.KioskRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;

@Service
@Transactional
public class KioskService {
	
	@Autowired
	private KioskRepository kioskRepository;
	@Autowired
	private Remote_KioskRepository remote_kioskRepository;
	
	@Autowired
	private  StoreInfoRepository storeInfoRepository;
	
	@Autowired
	private StoreInfoBuilder storeInfoBuilder;

	
	public void add(KioskRequest kioskRequest) {

		Kiosk kiosk = kioskRepository.findByKioskName(kioskRequest.getKioskName());
		if (kiosk != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "KioskName");
		}
		//validation for kioskId
		Kiosk kioskId = kioskRepository.findByKioskId(kioskRequest.getKioskId());
		if (kioskId != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "KioskId");
		}
	
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);

		kiosk = new Kiosk();
		kiosk.setId(longValue);
		kiosk.setKioskId(kioskRequest.getKioskId());
		kiosk.setKioskName(kioskRequest.getKioskName());
		kiosk.setBrandName(kioskRequest.getBrandName());
		kiosk.setModelName(kioskRequest.getModelName());
		kiosk.setCpu(kioskRequest.getCpu());
		kiosk.setHdd(kioskRequest.getHdd());
		kiosk.setRamMemory(kioskRequest.getRamMemory());
		kiosk.setScreenSize(kioskRequest.getScreenSize());
		kiosk.setIpAddress(kioskRequest.getIpAddress());
		kiosk.setMacAddress(kioskRequest.getMacAddress());
		kiosk.setActive(kioskRequest.isActive());

		kioskRepository.save(kiosk);
		
		//server
		Kiosk kiosk1 = remote_kioskRepository.findByKioskName(kioskRequest.getKioskName());
		if (kiosk1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "KioskName");
		}
		//validation for kioskId
		Kiosk kioskId1 = remote_kioskRepository.findByKioskId(kioskRequest.getKioskId());
		if (kioskId1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "KioskId");
		}
	
	

		kiosk1 = new Kiosk();
		kiosk1.setId(longValue);
		kiosk1.setKioskId(kioskRequest.getKioskId());
		kiosk1.setKioskName(kioskRequest.getKioskName());
		kiosk1.setBrandName(kioskRequest.getBrandName());
		kiosk1.setModelName(kioskRequest.getModelName());
		kiosk1.setCpu(kioskRequest.getCpu());
		kiosk1.setHdd(kioskRequest.getHdd());
		kiosk1.setRamMemory(kioskRequest.getRamMemory());
		kiosk1.setScreenSize(kioskRequest.getScreenSize());
		kiosk1.setIpAddress(kioskRequest.getIpAddress());
		kiosk1.setMacAddress(kioskRequest.getMacAddress());
		kiosk1.setActive(kioskRequest.isActive());

		remote_kioskRepository.save(kiosk1);
		
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	public List<KioskResponse> findAllUser() {
		// TODO Auto-generated method stub
		List<Kiosk> kiosks = (List<Kiosk>) kioskRepository.findAll();

		List<KioskResponse> kioskResponses = new ArrayList<KioskResponse>();

		for (Kiosk kiosk : kiosks) {
			kioskResponses.add(new KioskResponse(kiosk.getId(),kiosk.getKioskId(), kiosk.getKioskName(), kiosk.getBrandName(),
					kiosk.getModelName(), kiosk.getCpu(), kiosk.getHdd(), kiosk.getRamMemory(), kiosk.getScreenSize(),
					kiosk.getIpAddress(),kiosk.getMacAddress(),kiosk.isActive()));
		}
		return kioskResponses;
	}
	
	public void deleteByKiosk(Long Id) {
		kioskRepository.deleteById(Id);
	}
	
	public void updateKiosk(KioskRequest kioskRequest) {


		Kiosk kiosk = kioskRepository.findById(kioskRequest.getId()).get();

		kiosk.setKioskId(kioskRequest.getKioskId());
		kiosk.setKioskName(kioskRequest.getKioskName());
		kiosk.setBrandName(kioskRequest.getBrandName());
		kiosk.setModelName(kioskRequest.getModelName());
		kiosk.setCpu(kioskRequest.getCpu());
		kiosk.setHdd(kioskRequest.getHdd());
		kiosk.setRamMemory(kioskRequest.getRamMemory());
		kiosk.setScreenSize(kioskRequest.getScreenSize());
		kiosk.setIpAddress(kioskRequest.getIpAddress());
		kiosk.setMacAddress(kioskRequest.getMacAddress());
		kiosk.setActive(kioskRequest.isActive());
		
		kioskRepository.save(kiosk);

	}
	
	public List<KioskResponse> findUnassignedKiosk() {
		List<Kiosk> kiosks = (List<Kiosk>) kioskRepository.findByActive(true);
		List<KioskResponse> infoResponses = new ArrayList<KioskResponse>();
		for (Kiosk kiosk : kiosks) {
			if (kiosk != null && kiosk.getStoreinfok() == null) {
				infoResponses.add(new KioskResponse(kiosk.getId(),kiosk.getKioskId(), kiosk.getKioskName(), kiosk.getBrandName(),
						kiosk.getModelName(), kiosk.getCpu(), kiosk.getHdd(), kiosk.getRamMemory(), kiosk.getScreenSize(),
						kiosk.getIpAddress(),kiosk.getMacAddress(),kiosk.isActive()));
			}		
		}
		return infoResponses;
	}
	
	public String ipss() {
	
	     InetAddress ip;
	     String ipAddress = null;
	        try {

	            // get system name
	            String SystemName
	                = InetAddress.getLocalHost().getHostName();

	            System.out.println("System Name : "
	                               + SystemName);
	         
	        	
	            ip = InetAddress.getLocalHost();
	            System.out.println("System IP address : " + ip.getHostAddress());
	            ipAddress=ip.getHostAddress();
	            
	        }

	      catch (Exception e) {

	            e.printStackTrace();

	        }
	        
	        return ipAddress;
	
	}

	public Kiosk getKioskInfo(Long kioskid) {
		Optional<Kiosk> kiosk=kioskRepository.findById(kioskid);
		Kiosk kioskInfo = new Kiosk();
		if(kiosk.isPresent()) {
			kioskInfo=kiosk.get();
		}
		
		return kioskInfo;
	}

	public StoreInfoResponse kioskInfo(String storeName) {
		

		StoreInfo storeInfo = storeInfoRepository.findByStoreName(storeName);
		
		StoreInfoResponse storeInfoResponse = new StoreInfoResponse();

		
		List<Long> kIds = new ArrayList<Long>();
		for (Kiosk kiosk : storeInfo.getKiosk()) {
			kIds.add(kiosk.getId());
		}
		storeInfoResponse.setKioskIds(kIds);
		return storeInfoResponse;
	}

	public List<KioskResponse> KioskInfosys() throws SocketException, UnknownHostException {
	
		InetAddress ip;
      
        
            ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
  		
            System.out.println("dfghdf.."+sb.toString());
    		
    		List<Kiosk> k=kioskRepository.findByMacAddress(sb.toString());  		

    		List<KioskResponse> infoResponses = new ArrayList<KioskResponse>();
    		
    		for (Kiosk kiosk : k) {
   			
    			infoResponses.add(new KioskResponse(kiosk.getId(),kiosk.getKioskId(),kiosk.getKioskName(),kiosk.getBrandName(),kiosk.getModelName(),
    					kiosk.getCpu(),kiosk.getHdd(),kiosk.getRamMemory(),kiosk.getScreenSize(),kiosk.getIpAddress(),
    					kiosk.getMacAddress(),kiosk.isActive()));
    		}

    		return infoResponses;
		
	}
	
	
	public void assignStoretokiosk(Long storeId, Long kioskId) {
		
		StoreInfo storeInfo = storeInfoRepository.findById(storeId).get();

		Optional<Kiosk> optional = kioskRepository.findById(kioskId);
		
		if (optional.isPresent()) {
			Kiosk dbUser = optional.get();
			if (storeInfo.getKiosk().isEmpty()) {
				if(dbUser.getStoreinfok()==null){
				dbUser.setStoreinfok(storeInfo);
				}else {
					throw CommonException.CreateException(CommonExceptionMessage.ALREADY_ASSIGN);
				}
			} else {
				throw CommonException.CreateException(CommonExceptionMessage.ALREADY_ASSIGN);
			}
			
			kioskRepository.save(dbUser);
		   }	
			else {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_ASSIGN);
		}
	}

	
}
