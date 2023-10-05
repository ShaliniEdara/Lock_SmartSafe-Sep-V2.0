package com.safesmart.safesmart.remoterepository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safesmart.safesmart.model.SampleTable;

public interface Remote_SampleTableRepository  extends PagingAndSortingRepository<SampleTable, Long> {

	SampleTable findByName(String name);

}
