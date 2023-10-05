package com.safesmart.safesmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.model.StoreInfo;
@Repository
public interface StoreInfoRepository extends PagingAndSortingRepository<StoreInfo, Long> {

	StoreInfo findByStoreName(String string);
	
	StoreInfo findByStoreName(Long long1);
	
	List<StoreInfo> findByConfigured(boolean configured);
	
	List<StoreInfo> findByStatus(boolean status);
	
	
	StoreInfo findByStoreNameAndConfigured(String string, boolean configured);
	
	
	StoreInfo findByCorpStoreNo(String string);
	
	StoreInfo findBySerialNumber(String serialNumber);
	
	StoreInfo findByAccountNumber(String accountNumber);
	
    @Query("select id from StoreInfo")
	List<Long> getAllStoreIds();
    
    List<StoreInfo> findByUsers(Long id);

	
	
	
	
	
	
	
	

}
