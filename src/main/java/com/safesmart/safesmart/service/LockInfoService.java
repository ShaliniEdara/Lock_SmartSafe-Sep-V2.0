package com.safesmart.safesmart.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.model.LocksInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.remoterepository.Remote_LockInfoRepository;
import com.safesmart.safesmart.remoterepository.Remote_UserInfoRepository;
import com.safesmart.safesmart.repository.LockInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;

@Service
@Transactional
public class LockInfoService {

	@Autowired
	private LockInfoRepository lockInfoRepository;
	@Autowired
	private Remote_LockInfoRepository remote_lockInfoRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private Remote_UserInfoRepository remote_userInfoRepository;

	public void add(Long userId, String type) {
	
		UserInfo user = userInfoRepository.findById(userId).get();
		
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		LocksInfo lockinfo =new LocksInfo();
		
		lockinfo.setId(longValue);
		lockinfo.setUsers(user);
		lockinfo.setLockType(type);
		lockinfo.setLockTime(LocalDateTime.now());
		lockinfo.setLockStatus("Open");
		
		lockInfoRepository.save(lockinfo);
		
		//server
        UserInfo user1 = remote_userInfoRepository.findById(userId).get();
		
		LocksInfo lockinfo1 =new LocksInfo();
		
		lockinfo1.setId(longValue);
		lockinfo1.setUsers(user1);
		lockinfo1.setLockType(type);
		lockinfo1.setLockTime(LocalDateTime.now());
		lockinfo1.setLockStatus("Open");
		
		remote_lockInfoRepository.save(lockinfo1);
		
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }

}
