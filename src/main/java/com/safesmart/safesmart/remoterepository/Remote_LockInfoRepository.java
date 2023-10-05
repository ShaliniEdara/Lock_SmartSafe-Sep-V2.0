package com.safesmart.safesmart.remoterepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.LocksInfo;

@Repository
public interface Remote_LockInfoRepository extends PagingAndSortingRepository<LocksInfo, Long>  {

}
