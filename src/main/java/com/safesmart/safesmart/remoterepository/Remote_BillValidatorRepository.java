package com.safesmart.safesmart.remoterepository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Locks;

@Repository
public interface Remote_BillValidatorRepository extends PagingAndSortingRepository<BillValidator, Long> {

	BillValidator findByBillAcceptorName(String billAcceptorName);
	
	List<BillValidator> findByActive(boolean active);

	BillValidator findByBillAcceptorNo(String billAcceptorNo);

}
