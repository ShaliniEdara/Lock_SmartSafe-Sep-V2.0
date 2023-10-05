package com.safesmart.safesmart.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;

@Repository
public interface ValetDenominationsRepository extends PagingAndSortingRepository<ValetDenominations, Long>{
	
	ValetDenominations findByType(String type);
	ValetDenominations findByCreatedByAndType(UserInfo user,String MAINSAFE); 
	//ValetDenominations findByCreatedByAndType(UserInfo user,String SHIFTMANAGER); 
	
	
	

}
