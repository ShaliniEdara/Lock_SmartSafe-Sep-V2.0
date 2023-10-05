package com.safesmart.safesmart.scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.safesmart.safesmart.dto.ChangeRequestDto;
import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.service.ReportService;
import com.safesmart.safesmart.service.ChangeRequestService;
import com.safesmart.safesmart.util.EmailTemplate;
import com.safesmart.safesmart.service.TruckChangeRequestService;
import com.safesmart.safesmart.dto.TruckChangeRequestDto;

@Component
public class TodayShedular {

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ChangeRequestService changeRequestService;
	
	@Autowired
	private TruckChangeRequestService requestService;
	
	@Autowired
	private EmailTemplate emailTemplate;
	
	private ChangeRequestDto changeRequestDto;
	
	private TruckChangeRequestDto request;

	//@Scheduled(cron = "0 0/1 * * * ?")
	public void endOfDayReport() {
		System.out.println("endOfDayReport "+ LocalDate.now());
		reportService.endOfDayReport1();
	
	}

	//@Scheduled(cron = "0 0/1 * * * ?")
	public void endOfDayReport1() {
		System.out.println("Bank End Of Day Report "+ LocalDate.now());
		reportService.endOfDayReport2();
	
	}
//	@Scheduled(cron = "0 0/1 * * * ?")
		public void changeRequest() {
			System.out.println("Change Request "+ LocalDate.now());
			changeRequestService.createChangeRequest(changeRequestDto);
		
		}
//	@Scheduled(cron = "0 0/1 * * * ?")
	public void confirmchangeRequest() {
		System.out.println("Confirm Change Request "+ LocalDate.now());
		requestService.createTruckChangeRequest(request);
	
	}

	

	
	
	
}
