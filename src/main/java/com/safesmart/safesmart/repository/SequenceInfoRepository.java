package com.safesmart.safesmart.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safesmart.safesmart.model.SequenceInfo;

public interface SequenceInfoRepository extends PagingAndSortingRepository<SequenceInfo, Long> {

	SequenceInfo findByName(String string);

}
