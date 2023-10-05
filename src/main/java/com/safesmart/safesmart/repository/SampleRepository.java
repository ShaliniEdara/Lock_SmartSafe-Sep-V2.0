package com.safesmart.safesmart.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.safesmart.safesmart.model.SampleTable;

public interface SampleRepository  extends PagingAndSortingRepository<SampleTable,Long> {

}
