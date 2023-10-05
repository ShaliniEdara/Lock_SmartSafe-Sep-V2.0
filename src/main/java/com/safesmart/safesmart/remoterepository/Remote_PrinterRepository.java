  package com.safesmart.safesmart.remoterepository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.safesmart.safesmart.model.Printer;

@Repository
public interface Remote_PrinterRepository extends PagingAndSortingRepository<Printer, Long>{

	
	Printer findByPrinterName(String printerName);
	
	List<Printer> findByActive(boolean active);

	Printer findByPrinterNo(String printerNo);
}
