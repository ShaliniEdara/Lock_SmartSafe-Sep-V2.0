package com.safesmart.safesmart.repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.Locks;

@Repository
public interface LocksRepository extends PagingAndSortingRepository<Locks, Long> {

	Locks findByDigitalLockName(String digitalLockName);
	
	List<Locks> findByActive(boolean active);

	Locks findByDigitalLockNo(String digitalLockNo);
	

}
