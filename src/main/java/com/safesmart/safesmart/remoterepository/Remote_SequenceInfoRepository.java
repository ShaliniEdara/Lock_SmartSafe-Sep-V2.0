package com.safesmart.safesmart.remoterepository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safesmart.safesmart.model.SequenceInfo;

public interface Remote_SequenceInfoRepository extends PagingAndSortingRepository<SequenceInfo, Long> {

	SequenceInfo findByName(String string);

}
