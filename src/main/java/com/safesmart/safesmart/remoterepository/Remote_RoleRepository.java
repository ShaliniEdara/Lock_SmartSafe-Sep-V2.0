package com.safesmart.safesmart.remoterepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.Role;
@Repository
public interface Remote_RoleRepository  extends PagingAndSortingRepository<Role, Long>{

	Role findByName(String role);
  
  Role findByname(String name);
	
	Role findByDescription(String description);

	
	//Role findByDescription(String role);




}
