package com.safesmart.safesmart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.dto.EodResponse;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.service.BillValidatorService;
import com.safesmart.safesmart.service.DashBoardService;
import com.safesmart.safesmart.service.KioskService;
import com.safesmart.safesmart.service.LocksService;
import com.safesmart.safesmart.service.PrinterService;
import com.safesmart.safesmart.service.StoreInfoService;
import com.safesmart.safesmart.service.UserService;
@RestController
@RequestMapping(value = "/dashbordinfo")
@CrossOrigin
public class DashBoardController {
	@Autowired
	private StoreInfoService storeInfoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private KioskService kioskService;
	
	@Autowired
	private BillValidatorService billValidatorService;
	
	@Autowired
	private LocksService locksService;
	
	@Autowired
	private DashBoardService dashBoardService;

	@RequestMapping(value = "/AllDashBoardInfo/{storeName}/{toDay}",method = RequestMethod.GET)
	public EodResponse dashboardInfo(@PathVariable("storeName")String storeName, @PathVariable("toDay") boolean toDay ) throws IOException {
		//Eod reports and total Eodbills Amount
		EodResponse dashinfo=new EodResponse();
		List<EODReport> eodResponse=new ArrayList<>();
		eodResponse=dashBoardService.getEodReports(storeName,toDay);
		int grandTotal = 0;
		for (EODReport eodReport : eodResponse) {
			int totalValue=eodReport.getTotalValue();
			System.out.println(eodReport.getTotalValue());
		    grandTotal=grandTotal+ totalValue;
			
		}
		System.out.println(grandTotal);
		
		dashinfo.setTotalEodBillsAmount(grandTotal);
		dashinfo.setEodReport(eodResponse);
		
		//Total Store informatio
		StoreInfoResponse storeInfoResponse=dashBoardService.getAllStoreInfo(storeName);
		dashinfo.setStoreInfoResponse(storeInfoResponse);
		
		//Total StandBank Balance
		int totalStateBankBalance=dashBoardService.getTotalStateBankBalance(storeName,toDay);
		dashinfo.setStateBankBalance(totalStateBankBalance);
		
		//ChangeRequest Balance
		int changeRequestBalance=dashBoardService.getChageRequestBalance(storeName);
		System.out.println(changeRequestBalance);
		dashinfo.setChangeRequestBalance(changeRequestBalance);
		
		//MainSafe Balance 
		int mainSafeBalance =dashBoardService.getTotalMainSafeBalance(storeName, toDay);
		dashinfo.setMainSafeBalance(mainSafeBalance);
				
		//ShiftManager Balance
		int shiftManagerBalance = dashBoardService.getTotalShiftMangerBalance(storeName, toDay);
		dashinfo.setShiftManagerBalance(shiftManagerBalance);
				
		
		return dashinfo;
		
		
		
	}


	

}
