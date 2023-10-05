package com.safesmart.safesmart.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.Crop;
import com.safesmart.safesmart.model.Printer;

@Repository
public interface CropRepository extends PagingAndSortingRepository<Crop, Long> {
	
	Crop findByCropName(String cropName);

}
