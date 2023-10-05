package com.safesmart.safesmart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.print.attribute.standard.PrinterName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.dto.BillValidatorResponse;
import com.safesmart.safesmart.dto.LocksRequest;
import com.safesmart.safesmart.dto.LocksResponse;
import com.safesmart.safesmart.dto.PrinterRequest;
import com.safesmart.safesmart.dto.PrinterResponse;
import com.safesmart.safesmart.model.BillValidator;
import com.safesmart.safesmart.model.Locks;
import com.safesmart.safesmart.model.Printer;
import com.safesmart.safesmart.remoterepository.Remote_PrinterRepository;
import com.safesmart.safesmart.repository.PrinterRepository;

@Service
@Transactional
public class PrinterService {

	
	@Autowired
	private PrinterRepository printerRepository;
	@Autowired
	private Remote_PrinterRepository remote_printerRepository;
	
	public void add(PrinterRequest printerRequest) {

		Printer printer = printerRepository.findByPrinterName(printerRequest.getPrinterName());
		if (printer != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "PrinterName");
		}
		// validation for printerId
		Printer printerNo = printerRepository.findByPrinterNo(printerRequest.getPrinterNo());
		if (printerNo  != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "PrinterNo");
		}
	
		 UUID uuid = UUID.randomUUID();
		 Long longValue = uuidToLong(uuid);
		
		printer = new Printer();
		printer.setId(longValue);
		printer.setPrinterNo(printerRequest.getPrinterNo());
		printer.setPrinterName(printerRequest.getPrinterName());
		printer.setBrandName(printerRequest.getBrandName());
		printer.setModelName(printerRequest.getModelName());
		printer.setMachineType(printerRequest.getMachineType());
		printer.setPrintCapacity(printerRequest.getPrintCapacity());
	
		printer.setActive(printerRequest.isActive());

	
		printerRepository.save(printer);
		
		//server
		Printer printer1 = remote_printerRepository.findByPrinterName(printerRequest.getPrinterName());
		if (printer1 != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "PrinterName");
		}
		// validation for printerId
		Printer printerNo1 = remote_printerRepository.findByPrinterNo(printerRequest.getPrinterNo());
		if (printerNo1  != null) {
			throw CommonException.CreateException(CommonExceptionMessage.ALREADY_EXISTS, "PrinterNo");
		}
	

		printer1 = new Printer();
		printer1.setId(longValue);
		printer1.setPrinterNo(printerRequest.getPrinterNo());
		printer1.setPrinterName(printerRequest.getPrinterName());
		printer1.setBrandName(printerRequest.getBrandName());
		printer1.setModelName(printerRequest.getModelName());
		printer1.setMachineType(printerRequest.getMachineType());
		printer1.setPrintCapacity(printerRequest.getPrintCapacity());
	
		printer1.setActive(printerRequest.isActive());

	
		remote_printerRepository.save(printer1);
	}
	
	   public static long uuidToLong(UUID uuid) {
	        long mostSigBits = uuid.getMostSignificantBits();
	        long leastSigBits = uuid.getLeastSignificantBits();
	        long combinedValue = mostSigBits ^ leastSigBits;
	        return combinedValue % 1000000000000000L;//  last 15 digits
	    }
	
	public List<PrinterResponse> findAllUser() {
		// TODO Auto-generated method stub
		List<Printer> printers = (List<Printer>) printerRepository.findAll();

		List<PrinterResponse> printerResponses = new ArrayList<PrinterResponse>();
		for (Printer printer : printers) {
			printerResponses.add(new PrinterResponse(printer.getId(),printer.getPrinterNo(), printer.getPrinterName(), printer.getBrandName(),
					printer.getModelName(), printer.getMachineType(), printer.getPrintCapacity(), printer.isActive()));
		}
		return printerResponses;
	}
	
	public void deleteByPrinter(Long Id) {
		printerRepository.deleteById(Id);
	}
	
	public void updatePrinter(PrinterRequest printerRequest) {


		Printer printer = printerRepository.findById(printerRequest.getId()).get();

		printer.setPrinterNo(printerRequest.getPrinterNo());
		printer.setPrinterName(printerRequest.getPrinterName());
		printer.setBrandName(printerRequest.getBrandName());
		printer.setModelName(printerRequest.getModelName());
		printer.setMachineType(printerRequest.getMachineType());
		printer.setPrintCapacity(printerRequest.getPrintCapacity());
	
		printer.setActive(printerRequest.isActive());


		printerRepository.save(printer);

	}
	
	public List<PrinterResponse> findUnassignedPrinters() {
		List<Printer> printers = (List<Printer>) printerRepository.findByActive(true);
		List<PrinterResponse> infoResponses = new ArrayList<PrinterResponse>();
		for (Printer printer : printers) {
			if (printer != null && printer.getStoreinfop() == null) {
				infoResponses.add(new PrinterResponse(printer.getId(),printer.getPrinterNo(), printer.getPrinterName(), printer.getBrandName(),
						printer.getModelName(), printer.getMachineType(), printer.getPrintCapacity(), printer.isActive()));
			}
		}
		return infoResponses;
	}

	public  Printer getPrinterInfo(Long printerid) {;
		Optional<Printer> printer =printerRepository.findById(printerid);
	    Printer printerinfo =new Printer();
	    if(printer.isPresent()) {
	    	printerinfo=printer.get();
	    }
		return printerinfo;
	}

	
	
}
