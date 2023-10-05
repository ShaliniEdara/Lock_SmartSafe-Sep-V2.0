package com.safesmart.safesmart.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.dto.RoleConunt;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;

@Repository
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, Long> {

	UserInfo findByPassword(String password);

	UserInfo findByUsername(String username);

	UserInfo findByUsernameAndPassword(String username, String password);

	List<UserInfo> findByRole_Name(String role);

	List<UserInfo> findByRole_NameIn(List<String> roles);
	List<UserInfo> findByRole_NameAndStoreInfo(String roleNme,StoreInfo storeId);
	
	List<UserInfo> findByStoreInfo_StoreNameAndRole_NameIn(String storeInfo,List<String> roles);
	
	
	UserInfo findByMobile(String mobile);
	
	UserInfo findByEmail(String email);

	List<UserInfo> findByStoreInfo_StoreName(String storeInfo);

	
	List<UserInfo> findByStoreInfo_StoreNameAndRole_Name(String storeInfo,String role);

	
	List<UserInfo> findByStoreInfo_Id(Long id);
	
	@Query("SELECT u FROM UserInfo  u where storeInfo = :id")
	List<UserInfo> getAllUsers(Long id);
	List<UserInfo> findByStoreInfo(StoreInfo storeInfo);
	
	
	



	
	
   
}
