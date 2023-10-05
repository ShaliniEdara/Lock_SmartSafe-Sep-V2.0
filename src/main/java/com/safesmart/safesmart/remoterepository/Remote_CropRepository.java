package com.safesmart.safesmart.remoterepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.Crop;

@Repository
public interface Remote_CropRepository extends PagingAndSortingRepository<Crop, Long> {
	Crop findByCropName(String cropName);

}
