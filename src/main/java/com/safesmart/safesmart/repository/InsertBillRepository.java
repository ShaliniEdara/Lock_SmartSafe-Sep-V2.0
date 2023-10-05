package com.safesmart.safesmart.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.InsertBill;
@Repository
public interface InsertBillRepository extends  PagingAndSortingRepository<InsertBill, Long> {

	List<InsertBill> findByTransactionNumberIsNull();

	List<InsertBill> findByCreatedOn(LocalDate now);

	List<InsertBill> findByCreatedOnAndUser_IdIn(LocalDate now, List<Long> users);
	
	List<InsertBill> findByTransactionNumber(String transactionNumber);

	List<InsertBill> findByCreatedOnBetween(LocalDate minusDays, LocalDate now);

	List<InsertBill> findByUser_IdAndCreatedOnBetween(Long userId, LocalDate stDate, LocalDate endDate);
	
	List<InsertBill> findByUser_IdAndCreatedOn(Long userId, LocalDate now);

	List<InsertBill> findByUser_IdAndDateTimeBetween(Long userId, LocalDateTime stDate, LocalDateTime endDate);
	
	

}
