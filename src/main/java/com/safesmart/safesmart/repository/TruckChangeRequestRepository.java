package com.safesmart.safesmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.TruckChangeRequest;

@Repository
public interface TruckChangeRequestRepository
		extends PagingAndSortingRepository<TruckChangeRequest, Long> {

	Optional<TruckChangeRequest> findById(Long id);
	
	List<TruckChangeRequest> findAll();
}
