package com.safesmart.safesmart.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.RoleInfo;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xslf.util.DummyFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safesmart.safesmart.dto.BillResponse;
import com.safesmart.safesmart.dto.ChangeRequestDto;
import com.safesmart.safesmart.dto.ChangeValetDenominationsDto;
import com.safesmart.safesmart.dto.ChangeValetDenominationsDto1;
import com.safesmart.safesmart.dto.ChangedCurrencyDto;
import com.safesmart.safesmart.dto.DateRangedto;
import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.dto.EmployeeReportDto;
import com.safesmart.safesmart.dto.EmployeeReportResponse;
import com.safesmart.safesmart.dto.InsertBillResponse;
import com.safesmart.safesmart.dto.InsertBillsReportDto;
import com.safesmart.safesmart.dto.ManagerReportDto;
import com.safesmart.safesmart.dto.ReportDto;
import com.safesmart.safesmart.dto.ReprintReportDto;
import com.safesmart.safesmart.dto.StoreInfoRequest;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.mangerreport.changeValetDenomiDtoManger;
import com.safesmart.safesmart.mangerreport.changeValetDenomiDtoMangerChangeRe;
import com.safesmart.safesmart.mangerreport.denominationDto;
import com.safesmart.safesmart.model.ChangeRequest;
import com.safesmart.safesmart.model.ChangeValetDenominations;
import com.safesmart.safesmart.model.Dollar;
import com.safesmart.safesmart.model.InsertBill;
import com.safesmart.safesmart.model.SequenceInfo;
import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.model.ValetDenominations;
import com.safesmart.safesmart.repository.ChangeRequestRepository;
import com.safesmart.safesmart.repository.ChangeRquestDenominationsRepository;
import com.safesmart.safesmart.repository.InsertBillRepository;
import com.safesmart.safesmart.repository.RoleRepository;
import com.safesmart.safesmart.repository.SequenceInfoRepository;
import com.safesmart.safesmart.repository.StoreInfoRepository;
import com.safesmart.safesmart.repository.UserInfoRepository;
import com.safesmart.safesmart.repository.ValetDenominationsRepository;
import com.safesmart.safesmart.stand.standDto;
import com.safesmart.safesmart.stand.standResponse;
import com.safesmart.safesmart.util.DateUtil;
import com.safesmart.safesmart.util.EmailTemplate;

@Service
public class ReportService {

	@Autowired
	private StoreInfoService storeInfoService;

	@Autowired
	private SequenceInfoRepository sequenceInfoRepository;

	@Autowired
	private InsertBillRepository insertBillRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	ValetDenominationsRepository valetDenominationsRepository;

	@Autowired
	private EmailTemplate emailTemplate;
	
	@Autowired
	private ChangeRquestDenominationsRepository changeRequestDenominationsRepository;
	
	@Autowired
	private ChangeRequestRepository changeRequestRepo;
	
	@Autowired
	private  StoreInfoRepository storeInfoRepository;

	public ReprintReportDto rePrintReport(String storeName) {
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		System.out.println("rePrintReportStore "+storeName);
		ReprintReportDto reportDto = new ReprintReportDto();
		reportDto.setReportName("Reprint Receipt");
		reportDto.setStoreInfoResponse(storeInfoResponse);
	//	reportDto.setTimeStamp(LocalDateTime.now().toString());
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		reportDto.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));

		SequenceInfo sequenceInfo = sequenceInfoRepository.findByName("TRANSACTIONNO");
		
		List<InsertBill> insertBills = insertBillRepository.findByTransactionNumber(sequenceInfo.formatedValue());
		Map<String, InsertBillResponse> map = new HashMap<>();
		for (InsertBill bill : insertBills) {
			if (map.get(bill.getAmount()) != null) {
				InsertBillResponse insertBill = map.get(bill.getAmount());
				int count = insertBill.getCount() + 1;
				insertBill.setCount(count);
				map.put(bill.getAmount(), insertBill);
			} else {
				InsertBillResponse billResponse = new InsertBillResponse();
				billResponse.setAmount(bill.getAmount());
				billResponse.setCount(1);
				map.put(bill.getAmount(), billResponse);

			}

		}

		Collection<BillResponse> result = new ArrayList<BillResponse>();
		int count = 0;
		int sum = 0;
		for (Dollar dollar : Dollar.values()) {
			if (map.get(dollar.getDollar()) != null) {
				InsertBillResponse billResponse = map.get(dollar.getDollar());
				BillResponse response = new BillResponse();
				response.setCurrency(dollar.getDollar());
				response.setValue(billResponse.calculateSum(dollar.getValue()));
				response.setCount(billResponse.getCount());
				count = count + billResponse.getCount();
				sum = sum + response.getValue();
				result.add(response);
			}

		}
		BillResponse response = new BillResponse();
		response.setCurrency("All");
		response.setCount(count);
		response.setValue(sum);
		result.add(response);
		reportDto.setData(result);

		return reportDto;
	}

	public ReportDto testPrintReport(String storeName) {
		ReportDto reportDto = new ReportDto();
		reportDto.setReportName("Test Print Receipt");
		reportDto.setStoreInfoResponse(storeInfoService.getStoreInfoService(storeName));
	//	reportDto.setTimeStamp(LocalDateTime.now().toString());
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		reportDto.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));
		System.out.println("testPrinterStore "+storeName);
		return reportDto;
	}

	public InsertBillsReportDto insertBillsReport(String transactionNumber,String storeName) {

		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		System.out.println("storeName"+storeInfoResponse);
		InsertBillsReportDto reportDto = new InsertBillsReportDto();
		reportDto.setStoreInfoResponse(storeInfoResponse);
		reportDto.setReportName("Insert Bills Receipt");		
	//	reportDto.setTimeStamp(LocalDateTime.now().toString());
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		reportDto.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));

		List<InsertBill> insertBills = insertBillRepository.findByTransactionNumber(transactionNumber);
		Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
		for (InsertBill bill : insertBills) {
			if (map.get(bill.getAmount()) != null) {
				InsertBillResponse insertBill = map.get(bill.getAmount());
				int count = insertBill.getCount() + 1;
				insertBill.setCount(count);
				map.put(bill.getAmount(), insertBill);
			} else {
				InsertBillResponse billResponse = new InsertBillResponse();
				billResponse.setAmount(bill.getAmount());
				billResponse.setCount(1);
				map.put(bill.getAmount(), billResponse);

			}

		}
		Collection<BillResponse> result = new ArrayList<BillResponse>();
		int count = 0;
		int sum = 0;
		for (Dollar dollar : Dollar.values()) {
			if (map.get(dollar.getDollar()) != null) {
				InsertBillResponse billResponse = map.get(dollar.getDollar());
				BillResponse response = new BillResponse();
				response.setCurrency(dollar.getDollar());
				response.setValue(billResponse.calculateSum(dollar.getValue()));
				response.setCount(billResponse.getCount());
				count = count + billResponse.getCount();
				sum = sum + response.getValue();
				result.add(response);
			}

		}
		BillResponse response = new BillResponse();
		response.setCurrency("All");
		response.setCount(count);
		response.setValue(sum);
		result.add(response);
		reportDto.setData(result);

		return reportDto;

	}

	public EODReport endOfDayReport(Long userId) {

		StoreInfo storeInfo = new StoreInfo();
		Optional<UserInfo> optional = userInfoRepository.findById(userId);
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

		if (optional.isPresent()) {

			UserInfo dbUserInfo= optional.get();
		storeInfo =	dbUserInfo.getStoreInfo();
		}
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeInfo.getStoreName());
		EODReport reportDto = new EODReport();
	//	reportDto.setTimeStamp(LocalDateTime.now().toString());
		reportDto.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));
		reportDto.setStoreInfoResponse(storeInfoResponse);
		reportDto.setReportName("End of the Day Report");
		List<InsertBill> insertBills = insertBillRepository.findByCreatedOnAndUser_IdIn(LocalDate.now(), storeInfoResponse.getUserIds());

		Map<UserInfo, List<InsertBill>> userByBills = insertBills.stream()
				.collect(Collectors.groupingBy(InsertBill::getUser));

		List<EmployeeReportResponse> employeeData = new ArrayList<EmployeeReportResponse>();
		for (Map.Entry<UserInfo, List<InsertBill>> entry : userByBills.entrySet()) {
			EmployeeReportResponse er = new EmployeeReportResponse();
			Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
			for (InsertBill bill : entry.getValue()) {

				if (map.get(bill.getAmount()) != null) {
					InsertBillResponse insertBill = map.get(bill.getAmount());
					int count = insertBill.getCount() + 1;
					insertBill.setCount(count);
					map.put(bill.getAmount(), insertBill);
				} else {
					InsertBillResponse billResponse = new InsertBillResponse();
					billResponse.setAmount(bill.getAmount());
					billResponse.setCount(1);
					map.put(bill.getAmount(), billResponse);

				}

			}
			Collection<BillResponse> result = new ArrayList<BillResponse>();
			int count = 0;
			int sum = 0;
			for (Dollar dollar : Dollar.values()) {
				if (map.get(dollar.getDollar()) != null) {
					InsertBillResponse billResponse = map.get(dollar.getDollar());
					BillResponse response = new BillResponse();
					response.setCurrency(dollar.getDollar());
					response.setValue(billResponse.calculateSum(dollar.getValue()));
					response.setCount(billResponse.getCount());
					count = count + billResponse.getCount();
					sum = sum + response.getValue();
					result.add(response);
				}

			}
			BillResponse response = new BillResponse();
			response.setCurrency("All");
			response.setCount(count);
			response.setValue(sum);
			result.add(response);

			er.setName(entry.getKey().getUsername());
			er.setData(result);
			employeeData.add(er);
		}

		reportDto.setData(employeeData);

		return reportDto;
	}
	
	//Exporting EOD report to Excel
	public  ByteArrayInputStream EODReportToExcel(String storeName, boolean toDay) throws IOException {
		
		// the path variable boolean toDay indicates if the check box " TODAY "in the EOD reports is true or false 
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		List<Long> userIds = storeInfoResponse.getUserIds();
		String stTime = storeInfoResponse.getStartTime();
		String endTimes = storeInfoResponse.getEndTime();
		LocalTime startTime = LocalTime.parse(stTime);
		LocalTime endTime = LocalTime.parse(endTimes);
		LocalTime now = LocalTime.now();
		
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		LocalDate startDate = null;
		LocalDate endDate = null;
		// if check box Today is not checked then endDateTime will be assigned as certain conditions based on current time and Store default 
		// Open time and Close time 
	
		if(!toDay){
			
		/* now.compareTo(endTime) returns positive if now is greater than end time 
		 returns zero if equal 
		returns negative if now is less than end time */
		int diff = now.compareTo(endTime); 
		
		/*Logic for if report is generated after end time that current date report will be generated if 10:00 AM  is end time and report is generated at 10:01 AM then report will be from yesterday 10:00Am to todat 10 AM
		if report is generated at 09:59 AM then report will be from day before yesterday 10:00 AM to yesterday 10:00 AM */
		
		 endDate = (diff>0)|| (diff==0)?LocalDate.now():LocalDate.now().minusDays(1);
		
			endDateTime = endTime.atDate(endDate);
		}else {
			 	endDate = LocalDate.now();
				endDateTime = LocalTime.now().atDate(endDate);
		}
		//Checking = difference between start time and end time of a Store
				long hours = ChronoUnit.HOURS.between(startTime, endTime);
				long minutes
		        = ChronoUnit.MINUTES.between(startTime, endTime) % 60;
				long seconds
	            = ChronoUnit.SECONDS.between(startTime, endTime) % 60;
			// To determine the start date is before day or same day based on the store start time and end time
					if(hours<0 || minutes <0 || seconds <0 || (hours ==0 && minutes ==0 && seconds ==0)) {
					 startDate = endDate.minusDays(1);
					}else {
						startDate = endDate;
					}
					System.out.println(" Start Date is " + startDate + " End date is " + endDate);
					startDateTime = startTime.atDate(startDate);
			
		System.out.println(userIds);
		String[] columns = {"Store Name", "Store corp No", "Serial No"}; 
		try(
		     Workbook workbook = new XSSFWorkbook();
		     ByteArrayOutputStream out = new ByteArrayOutputStream();
		     ){
		     Sheet sheet = workbook.createSheet("report");
		     Font headerFont = workbook.createFont();
		     headerFont.setBold(true);
		     headerFont.setColor (IndexedColors.BLACK.getIndex());
		     
		     BorderStyle bS = BorderStyle.THIN;
		     CellStyle fullBold = workbook.createCellStyle();
		     fullBold.setBorderTop(bS);
		     fullBold.setBorderLeft(bS);
		     fullBold.setBorderBottom(bS);
		     fullBold.setBorderRight(bS);
		     fullBold.setFont(headerFont);
		     CellStyle full = workbook.createCellStyle();
		     full.setBorderBottom(bS);
		     full.setBorderLeft(bS);
		     full.setBorderRight(bS);
		     full.setBorderTop(bS);
		     CellStyle leftRight = workbook.createCellStyle();
		     leftRight.setBorderLeft(bS);
		     
		     Row headerRow1 = sheet.createRow(0);		     		
		     Cell cell1 = headerRow1.createCell(1);
		     cell1.setCellValue("EODREPORT");
		     cell1.setCellStyle(fullBold);
		     
		     int i = 1;
		     Row headerRow = sheet.createRow(i);
		     i++;
		     
		    	 Cell cell = headerRow.createCell(0);
		    	 cell.setCellValue(columns[0]);
		    	 cell.setCellStyle(fullBold);
		    	 
		    	 cell = headerRow.createCell(1);
		    	 cell.setCellValue(columns[1]);
		    	 cell.setCellStyle(fullBold);
		    	 
		    	 cell = headerRow.createCell(2);
		    	 cell.setCellValue(columns[2]);
		    	 cell.setCellStyle(fullBold);
		     	
		     //Row for printing Store details 
		  
		      Row detailsRow = sheet.createRow(i);
		      i++;
		    		String serialNo = storeInfoResponse.getSerialNumber();
		    		cell = detailsRow.createCell(0);
		    		cell.setCellValue(storeName);
		    		cell.setCellStyle(full);

		    		 cell = detailsRow.createCell(1);
		    		 cell.setCellValue(storeInfoResponse.getCorpStoreNo());
		    		 cell.setCellStyle(full);
		    		 
		    		 cell = detailsRow.createCell(2);
		    		 cell.setCellValue(serialNo);
		    		 cell.setCellStyle(full);
		    		 
		    // Row for printing start date and end date 
		      Row datesRow = sheet.createRow(i);
		      i+=3;
		      cell = datesRow.createCell(0);
		      cell.setCellValue("From  :" + startDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
		      cell.setCellStyle(full);
		      cell = datesRow.createCell(1);
		      cell.setCellStyle(full);
		      
		      cell = datesRow.createCell(2);
		      cell.setCellValue("To  :" + endDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
		      cell.setCellStyle(full);
		      
		      int grandTotal =0;
		      int grandCount =0;
		     for(Long userId : userIds) {
		      List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndDateTimeBetween(userId, startDateTime, endDateTime);
		      if(!insertBills.isEmpty()) {
		    	  
		    	  UserInfo user = userInfoRepository.findById(userId).get();
		    	  // Printing employee name 
		    	  Row userRow = sheet.createRow(i);
		    	  i++;
		    	  cell = userRow.createCell(0);
		    	  cell.setCellValue("Name");
		    	  cell.setCellStyle(fullBold);
		    	  cell = userRow.createCell(1);
		    	  cell.setCellValue(user.getFirstName() + " " + user.getLastName());
		    	  cell.setCellStyle(full);
		    	  //Row for printing headings 
		    	  Row headingsRow = sheet.createRow(i);
		    	  cell = headingsRow.createCell(0);
		    	  cell.setCellValue("Currency");
		    	  cell.setCellStyle(fullBold);
    	   
		    	  cell = headingsRow.createCell(1);
		    	  cell.setCellValue("Count");
		    	  cell.setCellStyle(fullBold);
    	   
		    	  cell = headingsRow.createCell(2);
		    	  cell.setCellValue("Value");
		    	  cell.setCellStyle(fullBold);
    	   
    	   Set<String> distinctDenominations =  new HashSet<String>();
    	   for(InsertBill bill : insertBills) {
    		   distinctDenominations.add(bill.getAmount());
    		   }
    	  i++;
    	  int totalCount =0;
    	  int sum = 0;
    	  //Adding Distinct denominations in to a set like $1,$2,...
    	   for(String a : distinctDenominations) {
    		   int count = 0;
    		   int product = 0;
    		   //checking number of notes of same denomination present in the current date bill
    		   for(InsertBill bill : insertBills) {
    			  if(a.equals(bill.getAmount())) {
    				  count++;
    			  }
    		   }
    		   //Row for printing values i.e., denomination ($1,$2,.....) , NO of notes (Count), Value ( denominations * Count)
    		   Row amountRow = sheet.createRow(i);
    		   cell = amountRow.createCell(0);
    		   cell.setCellValue(a);
    		   cell.setCellStyle(leftRight);
    		   
    		   amountRow.createCell(1).setCellValue(count);
    		   
    		   product = a.equals("$1")?1*count:a.equals("$2")?2*count:a.equals("$5")?5*count:a.equals("$10")?10*count:a.equals("$20")?20*count:
    			   a.equals("$50")?50*count:a.equals("$100")?100*count:1*count;
    		   
    		   
    		   cell = amountRow.createCell(2);
    		   cell.setCellValue("$" + Long.toString(product));
    		   cell.setCellStyle(leftRight);
    		   
    		   i++;
    		   totalCount+= count;
    		   sum+=product;
    	   
    	   	}
    	   // Row for printing current day total
    	   Row totalRow = sheet.createRow(i);
    	   
    	   cell = totalRow.createCell(0);
    	   cell.setCellValue("All");
    	   cell.setCellStyle(fullBold);
    	   
    	   cell = totalRow.createCell(1);
    	   cell.setCellValue(totalCount);
    	   cell.setCellStyle(full);
    	   
    	   cell = totalRow.createCell(2);
    	   cell.setCellValue("$" + Long.toString(sum));
    	   cell.setCellStyle(full);
    	   
    	   grandCount+= totalCount; 
    	    
    	   grandTotal +=sum;
    	   i+=2;
    	   
    	   }
		      
		      
		  }
		     // Row for printing grand total
		      Row grandTotalRow = sheet.createRow(i);
		      
		      cell = grandTotalRow.createCell(0);
		      cell.setCellValue("Total Bills Per Day");
		      cell.setCellStyle(fullBold);
		      
		     cell =  grandTotalRow.createCell(1);
		     cell.setCellValue(grandCount);
		     cell.setCellStyle(fullBold);
		      
		      cell = grandTotalRow.createCell(2);
		      cell.setCellValue("$" + Long.toString(grandTotal));
		      cell.setCellStyle(fullBold);
		      workbook.write(out);
		      if(!toDay) {
		      OutputStream fileOut = new FileOutputStream("D:\\newEODReport.xlsx");
		      workbook.write(fileOut);
		      }else {
		    	  OutputStream fileOut = new FileOutputStream("D:\\TodayEODReport.xlsx");
			      workbook.write(fileOut);
		      }
		
		
		
		return new ByteArrayInputStream(out.toByteArray());
		}
	}
	//Stand Bank report
	public  ByteArrayInputStream standBankReportExport(String storeName,String safeType, DateRangedto dateRangedto) throws IOException {
		/* This report is generated on the logic of comparing two consecutive records in the Change Valet denominations and defining which denomination 
		 * are removed and which denominations are added. If the next record is not available this method checks the current safe balance and determines
		 * which denominations are removed and which denominations are added. 
		 */
		
		/*Note: This report purely depends on the comparing 2 records and the removed amount might not be equal to the deposited value if total of 
		 * two consecutive records are not same 
		 * explanation : If the Total of two consecutive records are same then the amount deposited or removed might be not same which causes the deposited 
		 * amount might not be equal to removed change
		 */
		
		/*The records from the change Valet Denomination are retrieved based on the Requested safe and Created BY User and mentioned time period 
		 * Then again sorted depending on the time created just to run the required operation smoothly in case some records are edited by any manner 
		 */
		
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		ValetDenominations vD = valetDenominationsRepository.findByType(safeType);
		List<Long> userIds = storeInfoResponse.getUserIds();
		LocalDate sDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate eDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime time =  LocalTime.of(0,0,0);
		LocalTime time2 = LocalTime.of(23, 59,59);
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime startDateTime = time.atDate(sDate);
		LocalDateTime endDateTime = time2.atDate(eDate);
		UserInfo user;
		
			try(
			     Workbook workbook = new XSSFWorkbook();
			     ByteArrayOutputStream out = new ByteArrayOutputStream();
			     ){
				Sheet sheet = workbook.createSheet("report");
				sheet.createFreezePane(6,4);
			     Font headerFont = workbook.createFont();
			     headerFont.setBold(true);
			     
			     headerFont.setColor (IndexedColors.BLACK.getIndex());
			     
			     BorderStyle bS = BorderStyle.THIN;
			     CellStyle fullBold = workbook.createCellStyle();
			     fullBold.setBorderTop(bS);
			     fullBold.setBorderLeft(bS);
			     fullBold.setBorderBottom(bS);
			     fullBold.setBorderRight(bS);
			     fullBold.setFont(headerFont);
			     fullBold.setAlignment(HorizontalAlignment.CENTER);
			     fullBold.setVerticalAlignment(VerticalAlignment.CENTER);
			     CellStyle full = workbook.createCellStyle();
			     full.setBorderBottom(bS);
			     full.setBorderLeft(bS);
			     full.setBorderRight(bS);
			     full.setBorderTop(bS);
			     full.setAlignment(HorizontalAlignment.CENTER);
			     full.setVerticalAlignment(VerticalAlignment.CENTER);
			     CellStyle leftRight = workbook.createCellStyle();
			     leftRight.setBorderLeft(bS);

			     
			     Row headerRow1 = sheet.createRow(0);	
			     sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			     CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
			     RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
			     Cell cell1 = headerRow1.createCell(0);
			     cell1.setCellValue("STANDBANKREPORT_"+vD.getType());
			     cell1.setCellStyle(fullBold);
			     
			     Row headerRow = sheet.createRow(1);
			     Cell cellstore = headerRow.createCell(0);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));
				     CellRangeAddress region1 = new CellRangeAddress(1,1,0,1);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region1, sheet);
		          cellstore.setCellValue("Store Name");
		          cellstore.setCellStyle(fullBold);
			      
		          Cell cellstore2 = headerRow.createCell(2);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,2,3));
		          CellRangeAddress region2 = new CellRangeAddress(1,1,2,3);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region2, sheet);
		          cellstore2.setCellValue("Store corp No");
		          cellstore2.setCellStyle(fullBold);
			      
			      Cell cellstore4 = headerRow.createCell(4);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,4,5));
		          CellRangeAddress region3 = new CellRangeAddress(1,1,4,5);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region3, sheet);
		          cellstore4.setCellValue("Serial No");
		          cellstore4.setCellStyle(fullBold);
			      
			     //Row for printing Store details 
			      Row detailsRow = sheet.createRow(2);
			    		Cell cell = detailsRow.createCell(0);
			            sheet.addMergedRegion(new CellRangeAddress(2,2,0,1));
			            CellRangeAddress region4 = new CellRangeAddress(2,2,0,1);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region4, sheet);
			    		cell.setCellValue(storeName);
			    		cell.setCellStyle(full);

			    		 cell = detailsRow.createCell(2);
				         sheet.addMergedRegion(new CellRangeAddress(2,2,2,3));
				         CellRangeAddress region5 = new CellRangeAddress(2,2,2,3);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region5, sheet);
			    		 cell.setCellValue(storeInfoResponse.getCorpStoreNo());
			    		 cell.setCellStyle(full);
			    		 
			    		 cell = detailsRow.createCell(4);
				         sheet.addMergedRegion(new CellRangeAddress(2,2,4,5));
				         CellRangeAddress region6 = new CellRangeAddress(2,2,4,5);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region6, sheet);
				         String serialNo = storeInfoResponse.getSerialNumber();
			    		 cell.setCellValue(serialNo);
			    		 cell.setCellStyle(full);
			    		 
			    // Row for printing start date and end date 
			      Row datesRow = sheet.createRow(3);
			      
			      cell = datesRow.createCell(0);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,0,1));
		          CellRangeAddress region7 = new CellRangeAddress(3,3,0,1);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region7, sheet);
			      cell.setCellValue("From Date :" + sDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			      cell.setCellStyle(full);
			      
			      cell = datesRow.createCell(2);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,2,3));
		          CellRangeAddress region8 = new CellRangeAddress(3,3,2,3);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region8, sheet);
			      cell.setCellValue(" ");
			      cell.setCellStyle(full);
			      
			      cell = datesRow.createCell(4);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,4,5));
		          CellRangeAddress region9 = new CellRangeAddress(3,3,4,5);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region9, sheet);
			      cell.setCellValue("To Date :" + eDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			      cell.setCellStyle(full);
			      int i =4;
			      for(Long userId : userIds) {
						user = userInfoRepository.findById(userId).get();
						System.out.println("coming here");
					// This is the list of transactions done by a particular user present in the Change Valet Denominations table in between the given From and To dates. 
//						List<ChangeValetDenominations> list1 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndValetDenominations(user, startDateTime, endDateTime,vD);
					// This is the list of the transactions done by a particular user in between from date to the current date time
						List<ChangeValetDenominations> list2 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndValetDenominations(user, startDateTime, currentDateTime,vD);
						List<ChangeValetDenominations> list1 = new ArrayList<ChangeValetDenominations>();
						for(ChangeValetDenominations cVD : list2) {
							if(cVD.getCreated().compareTo(endDateTime)<1 ||cVD.getCreated().compareTo(endDateTime)==0 ) {
								list1.add(cVD);
								}
						}
						if(!list1.isEmpty()) {
						System.out.println("coming here 2");
						//Sorting the ChangeValetDenominations records based on time created 
						Collections.sort(list1, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
						
						Collections.sort(list2, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
		
				int size = list1.size();
				int size2 = list2.size();
				int j =0;
				while( j <size) {
				// Comparing every consecutive record 	
				ChangeValetDenominationsDto c1 = new ChangeValetDenominationsDto();
				BeanUtils.copyProperties(list1.get(j), c1);
				ChangeValetDenominationsDto c2 = new ChangeValetDenominationsDto();
				// to avoid index out of bound error we need to define up to when this operation should be carried out 
				if(j+1<size&& j+1<size2) {
					if(j+1<size) {
				BeanUtils.copyProperties(list1.get(j+1), c2);
					}else {
						BeanUtils.copyProperties(list2.get(j+1), c2);

					}
					
				// Checking which denominations are removed and which denominations are added in the requested Safe
				List<ChangedCurrencyDto> changes = c1.difference(c2); // This gives a list of changes made in the requested safe 
				// For reference check method "difference" defined in ChangeValetDenominationsDto class 
				
				// Printing which user performed the operation
				Row userRow = sheet.createRow(i);
			      i++;
			      cell = userRow.createCell(0);
//		          sheet.addMergedRegion(new CellRangeAddress(4,4,0,5));
//		          CellRangeAddress region10 = new CellRangeAddress(4,4,0,5);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region10, sheet);
			      cell.setCellValue("Name : "+user.getFirstName()+" " + user.getLastName());
			      cell.setCellStyle(fullBold);

				Row headingsRow = sheet.createRow(i);
			      i++;
			      cell = headingsRow.createCell(0);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,0,1));
//		          CellRangeAddress region11 = new CellRangeAddress(5,5,0,1);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region11, sheet);
			      cell.setCellValue(vD.getType());
			      cell.setCellStyle(fullBold);
			      cell = headingsRow.createCell(2);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,2,3));
//		          CellRangeAddress region12 = new CellRangeAddress(5,5,2,3);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region12, sheet);
			      cell.setCellValue("Change Needed");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow.createCell(4);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,4,5));
//		          CellRangeAddress region13 = new CellRangeAddress(5,5,4,5);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region13, sheet);
			      cell.setCellValue(vD.getType());
			      cell.setCellStyle(fullBold);
			      
			      
			  	Row headingsRow1 = sheet.createRow(i);
			      i++;
			      cell = headingsRow1.createCell(0);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(1);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(2);
			      cell.setCellValue("In");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(3);
			      cell.setCellValue("Out");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(4);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(5);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      
				for(ChangedCurrencyDto c:changes) {
					// printing the Changed values 
					Row valuesRow = sheet.createRow(i);
					i++;
					cell = valuesRow.createCell(0);
				    cell.setCellValue(c.getCurrency());
				    cell.setCellStyle(full);
					cell = valuesRow.createCell(1);
				    cell.setCellValue(c.getCurrencytotal());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(2);
				    cell.setCellValue(c.getDepositedValue());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(3);
				    cell.setCellValue(c.getChangeNeeded());
				    cell.setCellStyle(full);
					cell = valuesRow.createCell(4);
				    cell.setCellValue(c.getCurrency());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(5);
				    cell.setCellValue(c.getNewcurrencytotal());
				    cell.setCellStyle(full);
					}
					
				}
			//Comparing with Current balance of requested safe when c1 becomes the last record 
//				else{
//				List<ChangedCurrencyDto> lastChanges = c1.compareCurrentBal(vD);
//				Row userRow = sheet.createRow(i);
//			      i++;
//			      cell = userRow.createCell(0);
//			      cell.setCellValue("Name : ");
//			      cell.setCellStyle(fullBold);
//			      cell = userRow.createCell(1);
//			      cell.setCellValue(user.getFirstName()+" " + user.getLastName());
//			      cell.setCellStyle(fullBold);
//			      cell = userRow.createCell(2);
//			      cell.setCellValue(" ");
//			      cell.setCellStyle(full);
//				Row headingsRow = sheet.createRow(i);
//			      i++;
//			      cell = headingsRow.createCell(0);
//			      cell.setCellValue("");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(1);
//			      cell.setCellValue("");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(2);
//			      cell.setCellValue("In");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(3);
//			      cell.setCellValue("Out");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(4);
//			      cell.setCellValue("");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(5);
//			      cell.setCellValue("");
//			      cell.setCellStyle(fullBold);
//				for(ChangedCurrencyDto c:lastChanges) {
//					
//					Row valuesRow = sheet.createRow(i);
//					i++;
//					cell = valuesRow.createCell(0);
//				    cell.setCellValue(c.getCurrency());
//				    cell.setCellStyle(full);
//					cell = valuesRow.createCell(1);
//				    cell.setCellValue(c.getCurrencytotal());
//				    cell.setCellStyle(full);
//				    cell = valuesRow.createCell(2);
//				    cell.setCellValue(c.getDepositedValue());
//				    cell.setCellStyle(full);
//				    cell = valuesRow.createCell(3);
//				    cell.setCellValue(c.getChangeNeeded());
//				    cell.setCellStyle(full);
//					cell = valuesRow.createCell(4);
//				    cell.setCellValue(c.getCurrency());
//				    cell.setCellStyle(full);
//				    cell = valuesRow.createCell(5);
//				    cell.setCellValue(c.getNewcurrencytotal());
//				    cell.setCellStyle(full);
//					}
//				
//				}
				i++;
				j++;
			}
		}
			      }
			      OutputStream fileOut = new FileOutputStream("D:\\standBankReport.xlsx");
			      workbook.write(fileOut);
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
		}
}
	
	   //Change Request report	
      public  ByteArrayInputStream changeRequestReportExport(String storeName,String order_status, DateRangedto dateRangedto) throws IOException {

		
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		ChangeRequest cR = changeRequestRepo.findByOrderStatus(order_status);
		List<Long> userIds = storeInfoResponse.getUserIds();
		LocalDate sDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate eDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime time =  LocalTime.of(0,0,0);
		LocalTime time2 = LocalTime.of(23, 59,59);
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime startDateTime = time.atDate(sDate);
		LocalDateTime endDateTime = time2.atDate(eDate);
		UserInfo user;
		
		try(
			     Workbook workbook = new XSSFWorkbook();
			     ByteArrayOutputStream out = new ByteArrayOutputStream();
			     ){
				Sheet sheet = workbook.createSheet("report");
				sheet.createFreezePane(4,4);
			     Font headerFont = workbook.createFont();
			     headerFont.setBold(true);
			     
			     headerFont.setColor (IndexedColors.BLACK.getIndex());
			     
			     BorderStyle bS = BorderStyle.THIN;
			     CellStyle fullBold = workbook.createCellStyle();
			     fullBold.setBorderTop(bS);
			     fullBold.setBorderLeft(bS);
			     fullBold.setBorderBottom(bS);
			     fullBold.setBorderRight(bS);
			     fullBold.setFont(headerFont);
			     fullBold.setAlignment(HorizontalAlignment.CENTER);
			     fullBold.setVerticalAlignment(VerticalAlignment.CENTER);
			     CellStyle full = workbook.createCellStyle();
			     full.setBorderBottom(bS);
			     full.setBorderLeft(bS);
			     full.setBorderRight(bS);
			     full.setBorderTop(bS);
			     full.setAlignment(HorizontalAlignment.CENTER);
			     full.setVerticalAlignment(VerticalAlignment.CENTER);
			     CellStyle leftRight = workbook.createCellStyle();
			     leftRight.setBorderLeft(bS);

			     
			     Row headerRow1 = sheet.createRow(0);	
			     sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			     CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
			     RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
			     RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
			     Cell cell1 = headerRow1.createCell(0);
			     cell1.setCellValue("CHANGEREQUESTREPORT");
			     cell1.setCellStyle(fullBold);
			     
			     Row headerRow = sheet.createRow(1);
			     Cell cellstore = headerRow.createCell(0);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));
				     CellRangeAddress region1 = new CellRangeAddress(1,1,0,1);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region1, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region1, sheet);
		          cellstore.setCellValue("Store Name");
		          cellstore.setCellStyle(fullBold);
			      
		          Cell cellstore2 = headerRow.createCell(2);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,2,3));
		          CellRangeAddress region2 = new CellRangeAddress(1,1,2,3);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region2, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region2, sheet);
		          cellstore2.setCellValue("Store corp No");
		          cellstore2.setCellStyle(fullBold);
			      
			      Cell cellstore4 = headerRow.createCell(4);
		          sheet.addMergedRegion(new CellRangeAddress(1,1,4,5));
		          CellRangeAddress region3 = new CellRangeAddress(1,1,4,5);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region3, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region3, sheet);
		          cellstore4.setCellValue("Serial No");
		          cellstore4.setCellStyle(fullBold);
			      
			     //Row for printing Store details 
			      Row detailsRow = sheet.createRow(2);
			    		Cell cell = detailsRow.createCell(0);
			            sheet.addMergedRegion(new CellRangeAddress(2,2,0,1));
			            CellRangeAddress region4 = new CellRangeAddress(2,2,0,1);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region4, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region4, sheet);
			    		cell.setCellValue(storeName);
			    		cell.setCellStyle(full);

			    		 cell = detailsRow.createCell(2);
				         sheet.addMergedRegion(new CellRangeAddress(2,2,2,3));
				         CellRangeAddress region5 = new CellRangeAddress(2,2,2,3);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region5, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region5, sheet);
			    		 cell.setCellValue(storeInfoResponse.getCorpStoreNo());
			    		 cell.setCellStyle(full);
			    		 
			    		 cell = detailsRow.createCell(4);
				         sheet.addMergedRegion(new CellRangeAddress(2,2,4,5));
				         CellRangeAddress region6 = new CellRangeAddress(2,2,4,5);
					     RegionUtil.setBorderTop(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderBottom(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderLeft(BorderStyle.THIN, region6, sheet);
					     RegionUtil.setBorderRight(BorderStyle.THIN, region6, sheet);
				         String serialNo = storeInfoResponse.getSerialNumber();
			    		 cell.setCellValue(serialNo);
			    		 cell.setCellStyle(full);
			    		 
			    // Row for printing start date and end date 
			      Row datesRow = sheet.createRow(3);
			      
			      cell = datesRow.createCell(0);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,0,1));
		          CellRangeAddress region7 = new CellRangeAddress(3,3,0,1);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region7, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region7, sheet);
			      cell.setCellValue("From Date :" + sDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			      cell.setCellStyle(full);
			      
			      cell = datesRow.createCell(2);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,2,3));
		          CellRangeAddress region8 = new CellRangeAddress(3,3,2,3);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region8, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region8, sheet);
			      cell.setCellValue(" ");
			      cell.setCellStyle(full);
			      
			      cell = datesRow.createCell(4);
		          sheet.addMergedRegion(new CellRangeAddress(3,3,4,5));
		          CellRangeAddress region9 = new CellRangeAddress(3,3,4,5);
				     RegionUtil.setBorderTop(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderBottom(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderLeft(BorderStyle.THIN, region9, sheet);
				     RegionUtil.setBorderRight(BorderStyle.THIN, region9, sheet);
			      cell.setCellValue("To Date :" + eDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
			      cell.setCellStyle(full);
			      int i =4;
			      for(Long userId : userIds) {
						user = userInfoRepository.findById(userId).get();
						System.out.println("coming here");
						
						List<ChangeValetDenominations> list2 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndChangeRequest(user, startDateTime, currentDateTime,cR);
						List<ChangeValetDenominations> list1 = new ArrayList<ChangeValetDenominations>();
						for(ChangeValetDenominations cVD : list2) {
							if(cVD.getCreated().compareTo(endDateTime)<1 ||cVD.getCreated().compareTo(endDateTime)==0 ) {
								list1.add(cVD);
								}
						}
						if(!list1.isEmpty()) {
						System.out.println("coming here 2");
						//Sorting the ChangeValetDenominations records based on time created 
						Collections.sort(list1, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
						
						Collections.sort(list2, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
		
				int size = list1.size();
				int size2 = list2.size();
				int j =0;
				while( j <size) {
				// Comparing every consecutive record 	
					ChangeValetDenominationsDto1 c1 = new ChangeValetDenominationsDto1();
				BeanUtils.copyProperties(list1.get(j), c1);
				ChangeValetDenominationsDto1 c2 = new ChangeValetDenominationsDto1();
				// to avoid index out of bound error we need to define up to when this operation should be carried out 
				if(j+1<size&& j+1<size2) {
					if(j+1<size) {
				BeanUtils.copyProperties(list1.get(j+1), c2);
					}else {
						BeanUtils.copyProperties(list2.get(j+1), c2);

					}
					
				// Checking which denominations are removed and which denominations are added in the requested Safe
				List<ChangedCurrencyDto> changes = c1.difference(c2); // This gives a list of changes made in the requested safe 
				// For reference check method "difference" defined in ChangeValetDenominationsDto class 
				
				// Printing which user performed the operation
				Row userRow = sheet.createRow(i);
			      i++;
			      cell = userRow.createCell(0);
//		          sheet.addMergedRegion(new CellRangeAddress(4,4,0,5));
//		          CellRangeAddress region10 = new CellRangeAddress(4,4,0,5);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region10, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region10, sheet);
			      cell.setCellValue("Name : "+user.getFirstName()+" " + user.getLastName());
			      cell.setCellStyle(fullBold);

				Row headingsRow = sheet.createRow(i);
			      i++;
			      cell = headingsRow.createCell(0);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,0,1));
//		          CellRangeAddress region11 = new CellRangeAddress(5,5,0,1);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region11, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region11, sheet);
			      cell.setCellValue("MAINSAFE");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow.createCell(2);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,2,3));
//		          CellRangeAddress region12 = new CellRangeAddress(5,5,2,3);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region12, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region12, sheet);
			      cell.setCellValue("Change Needed");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow.createCell(4);
//		          sheet.addMergedRegion(new CellRangeAddress(5,5,4,5));
//		          CellRangeAddress region13 = new CellRangeAddress(5,5,4,5);
//				     RegionUtil.setBorderTop(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderBottom(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderLeft(BorderStyle.THIN, region13, sheet);
//				     RegionUtil.setBorderRight(BorderStyle.THIN, region13, sheet);
			      cell.setCellValue("MAINSAFE");
			      cell.setCellStyle(fullBold);
			      
			      
			  	Row headingsRow1 = sheet.createRow(i);
			      i++;
			      cell = headingsRow1.createCell(0);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(1);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(2);
			      cell.setCellValue("In");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(3);
			      cell.setCellValue("Out");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(4);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      cell = headingsRow1.createCell(5);
			      cell.setCellValue("");
			      cell.setCellStyle(fullBold);
			      
				for(ChangedCurrencyDto c:changes) {
					// printing the Changed values 
					Row valuesRow = sheet.createRow(i);
					i++;
					cell = valuesRow.createCell(0);
				    cell.setCellValue(c.getCurrency());
				    cell.setCellStyle(full);
					cell = valuesRow.createCell(1);
				    cell.setCellValue(c.getCurrencytotal());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(2);
				    cell.setCellValue(c.getDepositedValue());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(3);
				    cell.setCellValue(c.getChangeNeeded());
				    cell.setCellStyle(full);
					cell = valuesRow.createCell(4);
				    cell.setCellValue(c.getCurrency());
				    cell.setCellStyle(full);
				    cell = valuesRow.createCell(5);
				    cell.setCellValue(c.getNewcurrencytotal());
				    cell.setCellStyle(full);
					}
					
				}
			      
			//Comparing with Current balance of requested safe when c1 becomes the last record 
//				else{
//				List<ChangedCurrencyDto> lastChanges = c1.compareCurrentBal(cR);
//				Row userRow = sheet.createRow(i);
//			      i++;
//			      cell = userRow.createCell(0);
//			      cell.setCellValue("Name : ");
//			      cell.setCellStyle(fullBold);
//			      cell = userRow.createCell(1);
//			      cell.setCellValue(user.getFirstName()+" " + user.getLastName());
//			      cell.setCellStyle(fullBold);
//			      cell = userRow.createCell(2);
//			      cell.setCellValue(" ");
//			      cell.setCellStyle(full);
//	
//			    	   
//				Row headingsRow = sheet.createRow(i);
//			      i++;
//			      cell = headingsRow.createCell(0);
//			      cell.setCellValue("Denominations");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(1);
//			      cell.setCellValue("Change Requested");
//			      cell.setCellStyle(fullBold);
//			      cell = headingsRow.createCell(2);
//			      cell.setCellValue("Deposited Value");
//			      cell.setCellStyle(fullBold);
//				for(ChangedCurrencyDto c:lastChanges) {
//					
//					Row valuesRow = sheet.createRow(i);
//					i++;
//					cell = valuesRow.createCell(0);
//				    cell.setCellValue(c.getCurrency());
//				    cell.setCellStyle(full);
//				    cell = valuesRow.createCell(1);
//				    cell.setCellValue(c.getChangeNeeded());
//				    cell.setCellStyle(full);
//				    cell = valuesRow.createCell(2);
//				    cell.setCellValue(c.getDepositedValue());
//				    cell.setCellStyle(full);
//					}
//			    	   
//				
//				}
				i++;
				j++;
			}
		}
			      }
			      OutputStream fileOut = new FileOutputStream("D:\\changeRequestReport.xlsx");
			      workbook.write(fileOut);
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());
		}
}
	
	//Exporting Employees Reports to Excel 
	public  ByteArrayInputStream reportToExcel(Long userId, DateRangedto dateRangedto) throws IOException {

		UserInfo user = userInfoRepository.findById(userId).get();
		System.out.println(user.getStoreInfo().getStoreName());
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(user.getStoreInfo().getStoreName());
		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

		String[] columns = {"Store Name", "Store corp No", "Serial No"}; 
		try(
		     Workbook workbook = new XSSFWorkbook();
		     ByteArrayOutputStream out = new ByteArrayOutputStream();
		     ){
		     Sheet sheet = workbook.createSheet("report");
		     Font headerFont = workbook.createFont();
		     headerFont.setBold(true);
		     
		     headerFont.setColor (IndexedColors.BLACK.getIndex());

		     
		     BorderStyle bS = BorderStyle.THIN;
		     CellStyle fullBold = workbook.createCellStyle();
		     fullBold.setBorderTop(bS);
		     fullBold.setBorderLeft(bS);
		     fullBold.setBorderBottom(bS);
		     fullBold.setBorderRight(bS);
		     fullBold.setFont(headerFont);
		     CellStyle full = workbook.createCellStyle();
		     full.setBorderBottom(bS);
		     full.setBorderLeft(bS);
		     full.setBorderRight(bS);
		     full.setBorderTop(bS);
		     CellStyle leftRight = workbook.createCellStyle();
		     leftRight.setBorderLeft(bS);

		     
		     Row headerRow1 = sheet.createRow(0);		     		
		     Cell cell1 = headerRow1.createCell(1);
		     cell1.setCellValue("INSERTBILLREPORT");
		     cell1.setCellStyle(fullBold);
			     
		     Row headerRow = sheet.createRow(1);
		     for (int col=0; col<columns.length; col++) {
		    	 Cell cell = headerRow.createCell(col);
		    	 cell.setCellValue(columns[col]);
		    	 cell.setCellStyle(fullBold);
		     	}
		     //Row for printing Store details 
		      Row detailsRow = sheet.createRow(2);
		    	  	String storeName = storeInfoResponse.getStoreName();
		    		String serialNo = storeInfoResponse.getSerialNumber();
		    		Cell cell = detailsRow.createCell(0);
		    		cell.setCellValue(storeName);
		    		cell.setCellStyle(full);

		    		 cell = detailsRow.createCell(1);
		    		 cell.setCellValue(storeInfoResponse.getCorpStoreNo());
		    		 cell.setCellStyle(full);
		    		 
		    		 cell = detailsRow.createCell(2);
		    		 cell.setCellValue(serialNo);
		    		 cell.setCellStyle(full);
		    		 
		    // Row for printing start date and end date 
		      Row datesRow = sheet.createRow(3);
		      
		      cell = datesRow.createCell(0);
		      cell.setCellValue("From Date :" + stDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		      cell.setCellStyle(full);
		      
		      cell = datesRow.createCell(2);
		      cell.setCellValue("To Date :" + endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		      cell.setCellStyle(full);
		     
		      Row userRow = sheet.createRow(4);
		      cell = userRow.createCell(0);
		      cell.setCellValue("Employee Name : ");
		      cell.setCellStyle(fullBold);
		      cell = userRow.createCell(1);
		      cell.setCellValue(user.getFirstName()+" " + user.getLastName());
		      cell.setCellStyle(full);
		      List<LocalDate> totalDates = new ArrayList<>();
		      // Adding in between dates into a List
		      while (!stDate.isAfter(endDate)) {
		      
		    	  totalDates.add(stDate);
		          stDate = stDate.plusDays(1);
		      }
		      int grandTotal = 0;
		      int i = 5;
		      //Iterating date by date present in the list
		      for(LocalDate date : totalDates) {
		    	  
		    	   List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndCreatedOn(userId, date);
		    	   //Checking if any bills present in the current date if there are no bills go and check next date
		    	   if(!insertBills.isEmpty()) {
		    		   //Row for printing date 
		    		   Row dateRow = sheet.createRow(i);
			    	   i++;
		    	   cell = dateRow.createCell(0);
		    	   cell.setCellValue("Date: "+date.format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")));
		    	   cell.setCellStyle(fullBold);
		    	   //Row for printing headings 
		    	   Row headingsRow = sheet.createRow(i);
		    	   cell = headingsRow.createCell(0);
		    	   cell.setCellValue("Currency");
		    	   cell.setCellStyle(fullBold);
		    	   
		    	   cell = headingsRow.createCell(1);
		    	   cell.setCellValue("Count");
		    	   cell.setCellStyle(fullBold);
		    	   
		    	   cell = headingsRow.createCell(2);
		    	   cell.setCellValue("Value");
		    	   cell.setCellStyle(fullBold);
		    	   
		    	   Set<String> distinctDenominations =  new HashSet<String>();
		    	   for(InsertBill bill : insertBills) {
		    		   distinctDenominations.add(bill.getAmount());
		    		   }
		    	  i++;
		    	  int totalCount =0;
		    	  int sum = 0;
		    	  //Adding Distinct denominations in to a set like $1,$2,...
		    	   for(String a : distinctDenominations) {
		    		   int count = 0;
		    		   int product = 0;
		    		   //checking number of notes of same denomination present in the current date bill
		    		   for(InsertBill bill : insertBills) {
		    			  if(a.equals(bill.getAmount())) {
		    				  count++;
		    			  }
		    		   }
		    		   //Row for printing values i.e., denomination ($1,$2,.....) , NO of notes (Count), Value ( denominations * Count)
		    		   Row amountRow = sheet.createRow(i);
		    		   
		    		   cell = amountRow.createCell(0);
		    		   cell.setCellValue(a);
		    		   cell.setCellStyle(full);
		    		   
		    		   cell=amountRow.createCell(1);
		    		   cell.setCellValue(count);
		    		   cell.setCellStyle(full);

		    		   
		    		   product = a.equals("$1")?1*count:a.equals("$2")?2*count:a.equals("$5")?5*count:a.equals("$10")?10*count:a.equals("$20")?20*count:
		    			   a.equals("$50")?50*count:a.equals("$100")?100*count:1*count;
		    		   
		    		   
		    		  cell =  amountRow.createCell(2);
		    		  cell.setCellValue("$" + Long.toString(product));
		    		  cell.setCellStyle(full);
		    		   
		    		   i++;
		    		   totalCount+= count;
		    		   sum+=product;
		    	   
		    	   	}
		    	   // Row for printing current day total
		    	   Row totalRow = sheet.createRow(i);
		    	   
		    	   cell = totalRow.createCell(0);
		    	   cell.setCellValue("All");
		    	   cell.setCellStyle(full);
		    	   
		    	   cell = totalRow.createCell(1);
		    	   cell.setCellValue(totalCount);
		    	   cell.setCellStyle(full);
		    	   
		    	   cell = totalRow.createCell(2);
		    	   cell.setCellValue("$" + Long.toString(sum));
		    	   cell.setCellStyle(full);
		    	    
		    	   i+=2;
		    	   grandTotal+=sum;
		    	   
		    	   }
		      } 
		      // Row for printing grand total
		      Row grandTotalRow = sheet.createRow(i);
		      
		      cell = grandTotalRow.createCell(0);
		      cell.setCellValue("Total Bills ");
		      cell.setCellStyle(fullBold);
 		      cell = grandTotalRow.createCell(1);
 		      cell.setCellValue(" " );
 		      cell.setCellStyle(full);
		      cell = grandTotalRow.createCell(2);
		      cell.setCellValue("$" + Long.toString(grandTotal));
		      cell.setCellStyle(full);
		      workbook.write(out);

		      OutputStream fileOut = new FileOutputStream("D:\\InsertBillReport.xlsx");
		      workbook.write(fileOut);

		      return new ByteArrayInputStream(out.toByteArray());
		 } 
		

	}

	public EmployeeReportDto employeeReportData(Long userId, DateRangedto dateRangedto) {

		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

		List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndCreatedOnBetween(userId, stDate, endDate);
		Map<LocalDate, List<InsertBill>> userByBills = insertBills.stream()
				.collect(Collectors.groupingBy(InsertBill::getCreatedOn));

		EmployeeReportDto employeeReport = new EmployeeReportDto();
		employeeReport.setReportName("Employee Report");
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		
		// for storeName
		StoreInfo storeInfo = new StoreInfo();
		Optional<UserInfo> optional = userInfoRepository.findById(userId);
		if (optional.isPresent()) {

			UserInfo dbUserInfo= optional.get();
		storeInfo =	dbUserInfo.getStoreInfo();
		}
		String storeName = storeInfo.getStoreName();
		System.out.println(storeName);		
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		employeeReport.setStoreInfoResponse(storeInfoResponse);
	//	employeeReport.setTimeStamp(LocalDateTime.now().toString());
		employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));

		List<EmployeeReportResponse> employeeReportResponses = new ArrayList<EmployeeReportResponse>();
		for (Map.Entry<LocalDate, List<InsertBill>> entry : userByBills.entrySet()) {
			EmployeeReportResponse er = new EmployeeReportResponse();
			Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
			for (InsertBill bill : entry.getValue()) {

				if (map.get(bill.getAmount()) != null) {
					InsertBillResponse insertBill = map.get(bill.getAmount());
					int count = insertBill.getCount() + 1;
					insertBill.setCount(count);
					map.put(bill.getAmount(), insertBill);
				} else {
					InsertBillResponse billResponse = new InsertBillResponse();
					billResponse.setAmount(bill.getAmount());
					billResponse.setCount(1);
					map.put(bill.getAmount(), billResponse);

				}

			}
			Collection<BillResponse> result = new ArrayList<BillResponse>();
			int count = 0;
			int sum = 0;
			for (Dollar dollar : Dollar.values()) {
				if (map.get(dollar.getDollar()) != null) {
					InsertBillResponse billResponse = map.get(dollar.getDollar());
					BillResponse response = new BillResponse();
					response.setCurrency(dollar.getDollar());
					response.setValue(billResponse.calculateSum(dollar.getValue()));
					response.setCount(billResponse.getCount());
					count = count + billResponse.getCount();
					sum = sum + response.getValue();
					result.add(response);
				}

			}
			BillResponse response = new BillResponse();
			response.setCurrency("All");
			response.setCount(count);
			response.setValue(sum);
			result.add(response);
			er.setData(result);
			Date date = java.sql.Date.valueOf(entry.getKey());
			er.setName(myFormat.format(date));
			employeeReportResponses.add(er);
		}

		employeeReport.setData(employeeReportResponses);

		return employeeReport;
	}
	public ManagerReportDto managerBillReport(DateRangedto dateRangedto) {

		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

		List<InsertBill> insertBills = insertBillRepository.findByCreatedOnBetween(stDate, endDate);
		Map<UserInfo, List<InsertBill>> userByBills = insertBills.stream()
				.collect(Collectors.groupingBy(InsertBill::getUser));
		ManagerReportDto managerReport = new ManagerReportDto();
		List<EmployeeReportResponse> employeeReportResponses = new ArrayList<EmployeeReportResponse>();
		for (Map.Entry<UserInfo, List<InsertBill>> entry : userByBills.entrySet()) {
			EmployeeReportResponse er = new EmployeeReportResponse();
			Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
			for (InsertBill bill : entry.getValue()) {

				if (map.get(bill.getAmount()) != null) {
					InsertBillResponse insertBill = map.get(bill.getAmount());
					int count = insertBill.getCount() + 1;
					insertBill.setCount(count);
					map.put(bill.getAmount(), insertBill);
				} else {
					InsertBillResponse billResponse = new InsertBillResponse();
					billResponse.setAmount(bill.getAmount());
					billResponse.setCount(1);
					map.put(bill.getAmount(), billResponse);

				}

			}
			Collection<BillResponse> result = new ArrayList<BillResponse>();
			int count = 0;
			int sum = 0;
			for (Dollar dollar : Dollar.values()) {
				if (map.get(dollar.getDollar()) != null) {
					InsertBillResponse billResponse = map.get(dollar.getDollar());
					BillResponse response = new BillResponse();
					response.setCurrency(dollar.getDollar());
					response.setValue(billResponse.calculateSum(dollar.getValue()));
					response.setCount(billResponse.getCount());
					count = count + billResponse.getCount();
					sum = sum + response.getValue();
					result.add(response);
				}

			}
			BillResponse response = new BillResponse();
			response.setCurrency("All");
			response.setCount(count);
			response.setValue(sum);
			result.add(response);
			er.setData(result);
			er.setName(entry.getKey().getUsername());
			employeeReportResponses.add(er);
		}

		managerReport.setEmployeeReportResponses(employeeReportResponses);

		return managerReport;
	}

	

	public EmployeeReportDto managerReportData(Long userId, DateRangedto dateRangedto) {

		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

		List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndCreatedOnBetween(userId, stDate, endDate);
		Map<LocalDate, List<InsertBill>> userByBills = insertBills.stream()
				.collect(Collectors.groupingBy(InsertBill::getCreatedOn));

		EmployeeReportDto employeeReport = new EmployeeReportDto();
		employeeReport.setReportName("Manager Report");
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

		// for storeName
				StoreInfo storeInfo = new StoreInfo();
				Optional<UserInfo> optional = userInfoRepository.findById(userId);
				if (optional.isPresent()) {

					UserInfo dbUserInfo= optional.get();
				storeInfo =	dbUserInfo.getStoreInfo();
				}
				String storeName = storeInfo.getStoreName();
				System.out.println(storeName);		
				StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
				employeeReport.setStoreInfoResponse(storeInfoResponse);
			//	employeeReport.setTimeStamp(LocalDateTime.now().toString());
				employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));

				List<EmployeeReportResponse> employeeReportResponses = new ArrayList<EmployeeReportResponse>();
				for (Map.Entry<LocalDate, List<InsertBill>> entry : userByBills.entrySet()) {
					EmployeeReportResponse er = new EmployeeReportResponse();
					Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
					for (InsertBill bill : entry.getValue()) {

						if (map.get(bill.getAmount()) != null) {
							InsertBillResponse insertBill = map.get(bill.getAmount());
							int count = insertBill.getCount() + 1;
							insertBill.setCount(count);
							map.put(bill.getAmount(), insertBill);
						} else {
							InsertBillResponse billResponse = new InsertBillResponse();
							billResponse.setAmount(bill.getAmount());
							billResponse.setCount(1);
							map.put(bill.getAmount(), billResponse);

						}

					}
					Collection<BillResponse> result = new ArrayList<BillResponse>();
					int count = 0;
					int sum = 0;
					for (Dollar dollar : Dollar.values()) {
						if (map.get(dollar.getDollar()) != null) {
							InsertBillResponse billResponse = map.get(dollar.getDollar());
							BillResponse response = new BillResponse();
							response.setCurrency(dollar.getDollar());
							response.setValue(billResponse.calculateSum(dollar.getValue()));
							response.setCount(billResponse.getCount());
							count = count + billResponse.getCount();
							sum = sum + response.getValue();
							result.add(response);
						}

					}
					BillResponse response = new BillResponse();
					response.setCurrency("All");
					response.setCount(count);
					response.setValue(sum);
					result.add(response);
					er.setData(result);
					er.setName(entry.getKey().toString());
					employeeReportResponses.add(er);
				}

				employeeReport.setData(employeeReportResponses);

				return employeeReport;
			}
	
	// Kiosk StandBank MainSafe and Shiftmanager Report
	public standDto managerReportDataStandMain(String type, Long userId, DateRangedto dateRangedto) {
		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}
		ValetDenominations vD = valetDenominationsRepository.findByType(type);
		LocalTime time =  LocalTime.of(0,0,0);
		LocalTime time2 = LocalTime.of(23, 59,59);
	    LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime startDateTime = time.atDate(stDate);
		LocalDateTime endDateTime = time2.atDate(endDate);
		UserInfo user;
		
		standDto employeeReport = new standDto();
		employeeReport.setReportName("Manager Report");
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

		// for storeName
				StoreInfo storeInfo = new StoreInfo();
				Optional<UserInfo> optional = userInfoRepository.findById(userId);
				if (optional.isPresent()) {

					UserInfo dbUserInfo= optional.get();
				storeInfo =	dbUserInfo.getStoreInfo();
				}
				String storeName = storeInfo.getStoreName();
				System.out.println(storeName);		
				StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
				employeeReport.setStoreInfoResponse(storeInfoResponse);
			//	employeeReport.setTimeStamp(LocalDateTime.now().toString());
				employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));
		
				List<standResponse> employeeReportResponses = new ArrayList<standResponse>();
//			      for(Long userId : userIds) {
						user = userInfoRepository.findById(userId).get();
//						System.out.println("coming here");
					// This is the list of transactions done by a particular user present in the Change Valet Denominations table in between the given From and To dates. 
//						List<ChangeValetDenominations> list1 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndValetDenominations(user, startDateTime, endDateTime,vD);
					// This is the list of the transactions done by a particular user in between from date to the current date time
						List<ChangeValetDenominations> list2 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndValetDenominations(user, startDateTime, currentDateTime,vD);
						List<ChangeValetDenominations> list1 = new ArrayList<ChangeValetDenominations>();
						for(ChangeValetDenominations cVD : list2) {
							if(cVD.getCreated().compareTo(endDateTime)<1 ||cVD.getCreated().compareTo(endDateTime)==0 ) {
								list1.add(cVD);
								}
						}
						if(!list1.isEmpty()) {
						System.out.println("coming here 2");
						//Sorting the ChangeValetDenominations records based on time created 
						Collections.sort(list1, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
						
						Collections.sort(list2, new Comparator<ChangeValetDenominations>() {
						    @Override
						    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
						        return c1.getCreated().compareTo(c2.getCreated());
						    }
						});
		
				int size = list1.size();
				int size2 = list2.size();
				int j =0;
			//	while( j <size) {
				// Comparing every consecutive record 	
				changeValetDenomiDtoManger c1 = new changeValetDenomiDtoManger();
				BeanUtils.copyProperties(list1.get(j), c1);
				changeValetDenomiDtoManger c2 = new changeValetDenomiDtoManger();
				// to avoid index out of bound error we need to define up to when this operation should be carried out 
				if(j+1<size&& j+1<size2) {
					if(j+1<size) {
				BeanUtils.copyProperties(list1.get(j+1), c2);
					}else {
						BeanUtils.copyProperties(list2.get(j+1), c2);

					}
					
				// Checking which denominations are removed and which denominations are added in the requested Safe
				List<denominationDto> changes = c1.difference(c2); // This gives a list of changes made in the requested safe 
				// For reference check method "difference" defined in ChangeValetDenominationsDto class 
				standResponse er = new standResponse();
				Collection<denominationDto> result = new ArrayList<denominationDto>(); 
				
				for(denominationDto c:changes) {
					// printing the Changed values 
			
				   c.getDenominations();
				   c.getIn_Values();
				   c.getOut_Values();

				    result.add(c);
			
					}

		j++;
		er.setData(result);

		employeeReportResponses.add(er);
	}}
											
						System.out.println("stand"+type);
						System.out.println(userId);

						System.out.println(stDate);

						System.out.println(endDate);
						
      employeeReport.setData(employeeReportResponses);
						
		return employeeReport;
							
	}
	
	//Kiosk StandBank InterSafe Report
	public standDto managerReportDataStandInter(Long userId, DateRangedto dateRangedto) {
		
		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}
		LocalTime time =  LocalTime.of(0,0,0);
		LocalTime time2 = LocalTime.of(23, 59,59);
	    LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime startDateTime = time.atDate(stDate);
		LocalDateTime endDateTime = time2.atDate(endDate);
		UserInfo user;
		
		standDto employeeReport = new standDto();
		employeeReport.setReportName("Manager Report");
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		
		// for storeName
		StoreInfo storeInfo = new StoreInfo();
		Optional<UserInfo> optional = userInfoRepository.findById(userId);
		if (optional.isPresent()) {

			UserInfo dbUserInfo= optional.get();
		storeInfo =	dbUserInfo.getStoreInfo();
		}
		String storeName = storeInfo.getStoreName();
		System.out.println(storeName);		
		StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
		employeeReport.setStoreInfoResponse(storeInfoResponse);
	//	employeeReport.setTimeStamp(LocalDateTime.now().toString());
		employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));
		
		List<standResponse> employeeReportResponses = new ArrayList<standResponse>();
//	      for(Long userId : userIds) {
				user = userInfoRepository.findById(userId).get();
//				System.out.println("coming here");
			// This is the list of transactions done by a particular user present in the Change Valet Denominations table in between the given From and To dates. 
//				List<ChangeValetDenominations> list1 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndValetDenominations(user, startDateTime, endDateTime,vD);
			// This is the list of the transactions done by a particular user in between from date to the current date time
				List<ChangeValetDenominations> list2 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetween(user, startDateTime, currentDateTime);
				List<ChangeValetDenominations> list1 = new ArrayList<ChangeValetDenominations>();
				for(ChangeValetDenominations cVD : list2) {
					if(cVD.getCreated().compareTo(endDateTime)<1 ||cVD.getCreated().compareTo(endDateTime)==0 ) {
						list1.add(cVD);
						}
				}
				
				if(!list1.isEmpty()) {
				System.out.println("coming here 2");
				//Sorting the ChangeValetDenominations records based on time created 
				Collections.sort(list1, new Comparator<ChangeValetDenominations>() {
				    @Override
				    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
				        return c1.getCreated().compareTo(c2.getCreated());
				    }
				});
				
				Collections.sort(list2, new Comparator<ChangeValetDenominations>() {
				    @Override
				    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
				        return c1.getCreated().compareTo(c2.getCreated());
				    }
				});

		int size = list1.size();
		int size2 = list2.size();
		int j =0;
	//	while( j <size) {
		// Comparing every consecutive record 	
		changeValetDenomiDtoManger c1 = new changeValetDenomiDtoManger();
		BeanUtils.copyProperties(list1.get(j), c1);
		changeValetDenomiDtoManger c2 = new changeValetDenomiDtoManger();
		// to avoid index out of bound error we need to define up to when this operation should be carried out 
		if(j+1<size&& j+1<size2) {
			if(j+1<size) {
		BeanUtils.copyProperties(list1.get(j+1), c2);
			}else {
				BeanUtils.copyProperties(list2.get(j+1), c2);

			}
			
		// Checking which denominations are removed and which denominations are added in the requested Safe
		List<denominationDto> changes = c1.difference(c2); // This gives a list of changes made in the requested safe 
		// For reference check method "difference" defined in ChangeValetDenominationsDto class 
		standResponse er = new standResponse();
		Collection<denominationDto> result = new ArrayList<denominationDto>(); 
		
		for(denominationDto c:changes) {
			// printing the Changed values 
	
		   c.getDenominations();
		   c.getIn_Values();
		   c.getOut_Values();

		    result.add(c);
	
			}

j++;
er.setData(result);

employeeReportResponses.add(er);
}}
									
			
				System.out.println(userId);

				System.out.println(stDate);

				System.out.println(endDate);
				
employeeReport.setData(employeeReportResponses);
				
		
		return employeeReport;
	}
	
	
   // Kiosk ChangeRequest Report
	public standDto managerReportDataChangeRequest(String orderStatus, Long userId,DateRangedto dateRangedto) {
		
		LocalDate stDate = LocalDate.parse(dateRangedto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse(dateRangedto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(endDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}
		ChangeRequest cR = changeRequestRepo.findByOrderStatus(orderStatus);
		LocalTime time =  LocalTime.of(0,0,0);
		LocalTime time2 = LocalTime.of(23, 59,59);
	    LocalDateTime currentDateTime = LocalDateTime.now();
		
		LocalDateTime startDateTime = time.atDate(stDate);
		LocalDateTime endDateTime = time2.atDate(endDate);
		UserInfo user;
		
		standDto employeeReport = new standDto();
		employeeReport.setReportName("Manager Report");
		
		SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));

		// for storeName
				StoreInfo storeInfo = new StoreInfo();
				Optional<UserInfo> optional = userInfoRepository.findById(userId);
				if (optional.isPresent()) {

					UserInfo dbUserInfo= optional.get();
				storeInfo =	dbUserInfo.getStoreInfo();
				}
				String storeName = storeInfo.getStoreName();
				System.out.println(storeName);		
				StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
				employeeReport.setStoreInfoResponse(storeInfoResponse);
			//	employeeReport.setTimeStamp(LocalDateTime.now().toString());
				employeeReport.setTimeStamp(myFormat.format(Calendar.getInstance().getTime()));
		
				List<standResponse> employeeReportResponses = new ArrayList<standResponse>();	
//			 for(Long userId : userIds) {
				user = userInfoRepository.findById(userId).get();
				System.out.println("coming here");
				
				List<ChangeValetDenominations> list2 = changeRequestDenominationsRepository.findBycreatedByAndCreatedBetweenAndChangeRequest(user, startDateTime, currentDateTime,cR);
				List<ChangeValetDenominations> list1 = new ArrayList<ChangeValetDenominations>();
				for(ChangeValetDenominations cVD : list2) {
					if(cVD.getCreated().compareTo(endDateTime)<1 ||cVD.getCreated().compareTo(endDateTime)==0 ) {
						list1.add(cVD);
						}
				}
		//		if(!list1.isEmpty()) {
					System.out.println("coming here 2");
					//Sorting the ChangeValetDenominations records based on time created 
					Collections.sort(list1, new Comparator<ChangeValetDenominations>() {
					    @Override
					    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
					        return c1.getCreated().compareTo(c2.getCreated());
					    }
					});
					
					Collections.sort(list2, new Comparator<ChangeValetDenominations>() {
					    @Override
					    public int compare(ChangeValetDenominations c1, ChangeValetDenominations c2) {
					        return c1.getCreated().compareTo(c2.getCreated());
					    }
					});

				int size = list1.size();
				int size2 = list2.size();
				int j =0;
			//	while( j <size) {
				// Comparing every consecutive record 	
				changeValetDenomiDtoMangerChangeRe c1 = new changeValetDenomiDtoMangerChangeRe();
				BeanUtils.copyProperties(list1.get(j), c1);
				changeValetDenomiDtoMangerChangeRe c2 = new changeValetDenomiDtoMangerChangeRe();
				// to avoid index out of bound error we need to define up to when this operation should be carried out 
				if(j+1<size&& j+1<size2) {
					if(j+1<size) {
				BeanUtils.copyProperties(list1.get(j+1), c2);
					}else {
						BeanUtils.copyProperties(list2.get(j+1), c2);

					}
					
				// Checking which denominations are removed and which denominations are added in the requested Safe
				List<denominationDto> changes = c1.difference(c2); // This gives a list of changes made in the requested safe 
				// For reference check method "difference" defined in ChangeValetDenominationsDto class 
				standResponse er = new standResponse();
				Collection<denominationDto> result = new ArrayList<denominationDto>(); 
				
				for(denominationDto c:changes) {
					// printing the Changed values 
			
				   c.getDenominations();
				   c.getIn_Values();
				   c.getOut_Values();

				    result.add(c);
			
					}

		j++;
		er.setData(result);

		employeeReportResponses.add(er);
	}
											
						System.out.println(orderStatus);
						System.out.println(userId);

						System.out.println(stDate);

						System.out.println(endDate);
						
      employeeReport.setData(employeeReportResponses);
						
		return employeeReport;
							
	}

	

	// EOD Report with Employee Data for Owner
	public void endOfDayReport1() {
		System.out.println("service method called");
		List<StoreInfoResponse> storesList = storeInfoService.findAssignedStores();
		List<StoreInfoResponse> selectedStoresList = new ArrayList<StoreInfoResponse>();
		if (storesList != null) {
			for (StoreInfoResponse storeInfoResponse : storesList) {

				if (storeInfoResponse.getEndTime().equalsIgnoreCase("06:00")) {

					selectedStoresList.add(storeInfoResponse);
				}
			}
		}

		for (StoreInfoResponse storeInfoResponse : storesList) {

			int totalCount = 0;
			int totalValue = 0;
			EODReport reportDto = new EODReport();
			reportDto.setTimeStamp(LocalDateTime.now().toString());
			reportDto.setStoreInfoResponse(storeInfoResponse);
			reportDto.setReportName("End of the Day Report");
			List<InsertBill> insertBills = insertBillRepository.findByCreatedOnAndUser_IdIn(LocalDate.now(),
					storeInfoResponse.getUserIds());

			Map<UserInfo, List<InsertBill>> userByBills = insertBills.stream()
					.collect(Collectors.groupingBy(InsertBill::getUser));

			List<EmployeeReportResponse> employeeData = new ArrayList<EmployeeReportResponse>();
			for (Map.Entry<UserInfo, List<InsertBill>> entry : userByBills.entrySet()) {
				EmployeeReportResponse er = new EmployeeReportResponse();
				Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
				for (InsertBill bill : entry.getValue()) {

					if (map.get(bill.getAmount()) != null) {
						InsertBillResponse insertBill = map.get(bill.getAmount());
						int count = insertBill.getCount() + 1;
						insertBill.setCount(count);
						map.put(bill.getAmount(), insertBill);
					} else {
						InsertBillResponse billResponse = new InsertBillResponse();
						billResponse.setAmount(bill.getAmount());
						billResponse.setCount(1);
						map.put(bill.getAmount(), billResponse);

					}

				}
				Collection<BillResponse> result = new ArrayList<BillResponse>();
				int count = 0;
				int sum = 0;
				for (Dollar dollar : Dollar.values()) {
					if (map.get(dollar.getDollar()) != null) {
						InsertBillResponse billResponse = map.get(dollar.getDollar());
						BillResponse response = new BillResponse();
						response.setCurrency(dollar.getDollar());
						response.setValue(billResponse.calculateSum(dollar.getValue()));
						response.setCount(billResponse.getCount());
						count = count + billResponse.getCount();
						sum = sum + response.getValue();
						result.add(response);
					}

				}
				BillResponse response = new BillResponse();
				response.setCurrency("All");
				response.setCount(count);
				response.setValue(sum);
				result.add(response);

				totalCount = totalCount + count;
				totalValue = totalValue + sum;
				er.setName(entry.getKey().getUsername());
				er.setData(result);
				employeeData.add(er);
			}

			reportDto.setData(employeeData);
			reportDto.setTotalCount(totalCount);
			reportDto.setTotalValue(totalValue);

			emailTemplate.createEndOfDayMail(reportDto);
		}

	}



//EOD Report with Employee Data for Bank
	public void endOfDayReport2() {
		System.out.println("Bank service method called");
		List<StoreInfoResponse> storesList = storeInfoService.findAssignedStores();
		List<StoreInfoResponse> selectedStoresList = new ArrayList<StoreInfoResponse>();
		if (storesList != null) {
			for (StoreInfoResponse storeInfoResponse : storesList) {

				if (storeInfoResponse.getEndTime().equalsIgnoreCase("06:00")) {

					selectedStoresList.add(storeInfoResponse);
				}
			}
		}

		for (StoreInfoResponse storeInfoResponse : storesList) {

			int totalCount = 0;
			int totalValue = 0;
			EODReport reportDto = new EODReport();
			reportDto.setTimeStamp(LocalDateTime.now().toString());
			reportDto.setStoreInfoResponse(storeInfoResponse);
			reportDto.setReportName("End of the Day Bank Report");
			List<InsertBill> insertBills = insertBillRepository.findByCreatedOnAndUser_IdIn(LocalDate.now(),
					storeInfoResponse.getUserIds());

			Map<UserInfo, List<InsertBill>> userByBills = insertBills.stream()
					.collect(Collectors.groupingBy(InsertBill::getUser));

			List<EmployeeReportResponse> employeeData = new ArrayList<EmployeeReportResponse>();
			for (Map.Entry<UserInfo, List<InsertBill>> entry : userByBills.entrySet()) {
				EmployeeReportResponse er = new EmployeeReportResponse();
				Map<String, InsertBillResponse> map = new HashMap<String, InsertBillResponse>();
				for (InsertBill bill : entry.getValue()) {

					if (map.get(bill.getAmount()) != null) {
						InsertBillResponse insertBill = map.get(bill.getAmount());
						int count = insertBill.getCount() + 1;
						insertBill.setCount(count);
						map.put(bill.getAmount(), insertBill);
					} else {
						InsertBillResponse billResponse = new InsertBillResponse();
						billResponse.setAmount(bill.getAmount());
						billResponse.setCount(1);
						map.put(bill.getAmount(), billResponse);

					}

				}
				Collection<BillResponse> result = new ArrayList<BillResponse>();
				int count = 0;
				int sum = 0;
				for (Dollar dollar : Dollar.values()) {
					if (map.get(dollar.getDollar()) != null) {
						InsertBillResponse billResponse = map.get(dollar.getDollar());
						BillResponse response = new BillResponse();
						response.setCurrency(dollar.getDollar());
						response.setValue(billResponse.calculateSum(dollar.getValue()));
						response.setCount(billResponse.getCount());
						count = count + billResponse.getCount();
						sum = sum + response.getValue();
						result.add(response);
					}

				}
				BillResponse response = new BillResponse();
				response.setCurrency("All");
				response.setCount(count);
				response.setValue(sum);
				result.add(response);

				totalCount = totalCount + count;
				totalValue = totalValue + sum;
				er.setName(entry.getKey().getUsername());
				er.setData(result);
				employeeData.add(er);
			}

			reportDto.setData(employeeData);
			reportDto.setTotalCount(totalCount);
			reportDto.setTotalValue(totalValue);

			emailTemplate.createEndOfDayBankMail(reportDto);
		}

	}
    //Eod reports for charts
	public Map<Set<String>, List<Integer>> getEodReportsData(String storeName, boolean toDay) {
		Map<Set<String>, List<Integer>> userreports=new HashMap();
		
		// the path variable boolean toDay indicates if the check box " TODAY "in the EOD reports is true or false 
				StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
				List<Long> userIds = storeInfoResponse.getUserIds();
			    Set<String> userNames=new HashSet();
				List<Integer> userTotallAmount =new ArrayList();
				//int sum = 0;
                String stTime = storeInfoResponse.getStartTime();
				String endTimes = storeInfoResponse.getEndTime();
				LocalTime startTime = LocalTime.parse(stTime);
				LocalTime endTime = LocalTime.parse(endTimes);
				LocalTime now = LocalTime.now();
				LocalDateTime startDateTime = null;
				LocalDateTime endDateTime = null;
				LocalDate startDate = null;
				LocalDate endDate = null;
				// if check box Today is not checked then endDateTime will be assigned as certain conditions based on current time and Store default 
				// Open time and Close time 
				if(!toDay){
					
					/* now.compareTo(endTime) returns positive if now is greater than end time 
					 returns zero if equal 
					returns negative if now is less than end time */
					int diff = now.compareTo(endTime); 
					
					/*Logic for if report is generated after end time that current date report will be generated if 10:00 AM  is end time and report is generated at 10:01 AM then report will be from yesterday 10:00Am to todat 10 AM
					if report is generated at 09:59 AM then report will be from day before yesterday 10:00 AM to yesterday 10:00 AM */
					
					 endDate = (diff>0)|| (diff==0)?LocalDate.now():LocalDate.now().minusDays(1);
					
						endDateTime = endTime.atDate(endDate);
					}else {
						 	endDate = LocalDate.now();
							endDateTime = LocalTime.now().atDate(endDate);
					}
					//Checking = difference between start time and end time of a Store
							long hours = ChronoUnit.HOURS.between(startTime, endTime);
							long minutes
					        = ChronoUnit.MINUTES.between(startTime, endTime) % 60;
							long seconds
				            = ChronoUnit.SECONDS.between(startTime, endTime) % 60;
						// To determine the start date is before day or same day based on the store start time and end time
								if(hours<0 || minutes <0 || seconds <0 || (hours ==0 && minutes ==0 && seconds ==0)) {
								 startDate = endDate.minusDays(1);
								}else {
									startDate = endDate;
								}
								System.out.println(" Start Date is " + startDate + " End date is " + endDate);
								startDateTime = startTime.atDate(startDate);
						
					System.out.println("-------Assign users ids are ----"+userIds);
					int grandTotal =0;
				      int grandCount =0;
					
					for(Long userId : userIds) {
					      List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndDateTimeBetween(userId, startDateTime, endDateTime);
					      if(!insertBills.isEmpty()) {
					    	  //System.out.println("-----------------we are in if block -------------------");
					    	  UserInfo user = userInfoRepository.findById(userId).get();
					    	  userNames.add(user.getUsername());
					    	System.out.println("--------------Users informations are----------------"+userNames);
                            Set<String> distinctDenominations =  new HashSet<String>();
					    	   for(InsertBill bill : insertBills) {
				    		   distinctDenominations.add(bill.getAmount());
				    		   //System.out.println("------we are in for loop ending stage---------");
					    		   }
					    	   System.out.println("The "+userNames+"denaminations are"+distinctDenominations);
//					    	  //i++;
					    	  int totalCount =0;
					    	  int sum = 0;
					    	  //Adding Distinct denominations in to a set like $1,$2,...
					    	   for(String a : distinctDenominations) {
 				    		      int count = 0;
					    		   int product = 0;
					    		   //checking number of notes of same denomination present in the current date bill
					    		   for(InsertBill bill : insertBills) {
					    		     //System.out.println("--------we are in  find  the cunt sor loop-----------");
					    			  if(a.equals(bill.getAmount())) {
					    				  count++;
					    				  }
					    			  }
					    		   System.out.println(count);
					    		   product = a.equals("$1")?1*count:a.equals("$2")?2*count:a.equals("$5")?5*count:a.equals("$10")?10*count:a.equals("$20")?20*count:
					    			   a.equals("$50")?50*count:a.equals("$100")?100*count:1*count;  
					    		   totalCount+= count;
					    		   sum+=product;
					    		  
					    		   }
					    	   //grandCount+= totalCount; 
					    	    
					    	   //grandTotal +=sum;
					    	   userTotallAmount.add(sum);
					    	   //System.out.println(grandTotal);
					    	   //System.out.println(grandCount);
					      }
					      
					      
				    	 }
					
					userreports.put(userNames, userTotallAmount);
		
		return userreports;
	}
//  //EOD reports for Charts through Eod object
//	public List<EODReport>   getEodReportsChartsData(String storeName, boolean toDay) {
//		List<EODReport> eodReports=new ArrayList<>();
//		
//		
//		for (int i=0;i<3;i++) {
//			EODReport eod=new EODReport();
//			eod.setTotalValue(i);
//			eodReports.add(eod);	
//		}
//		
//		
//		return eodReports;
//	}
	
	//EOD reports for Charts through Eod object
		public List<EODReport>   getEodReportsChartsData(String storeName, boolean toDay) {
			List<EODReport> eodReports=new ArrayList<>();
			//EODReport eod=new EODReport();
			   StoreInfoResponse storeInfoResponse = storeInfoService.getStoreInfoService(storeName);
				List<Long> userIds = storeInfoResponse.getUserIds();
				//Set<String> userNames=new HashSet();
				//List<Integer> userTotallAmount =new ArrayList();
				//int sum = 0;
				String stTime = storeInfoResponse.getStartTime();
				String endTimes = storeInfoResponse.getEndTime();
				LocalTime startTime = LocalTime.parse(stTime);
				LocalTime endTime = LocalTime.parse(endTimes);
				LocalTime now = LocalTime.now();
				LocalDateTime startDateTime = null;
				LocalDateTime endDateTime = null;
				LocalDate startDate = null;
				LocalDate endDate = null;
				// if check box Today is not checked then endDateTime will be assigned as certain conditions based on current time and Store default 
				// Open time and Close time 
				if(!toDay){
					
					/* now.compareTo(endTime) returns positive if now is greater than end time 
					 returns zero if equal 
					returns negative if now is less than end time */
					int diff = now.compareTo(endTime); 
					
					/*Logic for if report is generated after end time that current date report will be generated if 10:00 AM  is end time and report is generated at 10:01 AM then report will be from yesterday 10:00Am to todat 10 AM
					if report is generated at 09:59 AM then report will be from day before yesterday 10:00 AM to yesterday 10:00 AM */
					
					 endDate = (diff>0)|| (diff==0)?LocalDate.now():LocalDate.now().minusDays(1);
					
						endDateTime = endTime.atDate(endDate);
					}else {
						 	endDate = LocalDate.now();
							endDateTime = LocalTime.now().atDate(endDate);
					}
					//Checking = difference between start time and end time of a Store
							long hours = ChronoUnit.HOURS.between(startTime, endTime);
							long minutes
					        = ChronoUnit.MINUTES.between(startTime, endTime) % 60;
							long seconds
				            = ChronoUnit.SECONDS.between(startTime, endTime) % 60;
						// To determine the start date is before day or same day based on the store start time and end time
								if(hours<0 || minutes <0 || seconds <0 || (hours ==0 && minutes ==0 && seconds ==0)) {
								 startDate = endDate.minusDays(1);
								}else {
									startDate = endDate;
								}
								
								startDateTime = startTime.atDate(startDate);
					            System.out.println("----------The Assigin users id are--------------"+userIds);
					            System.out.println(" The "+storeName+"start Date is " + startDate + "and  End date is " + endDate);
					            System.out.println("The Assign users are between Start date and End date of "+storeName);
					 int grandTotal =0;
				     int grandCount =0;
					for(Long userId : userIds) {
						EODReport eod=new EODReport();
					      List<InsertBill> insertBills = insertBillRepository.findByUser_IdAndDateTimeBetween(userId, startDateTime, endDateTime);
					      if(!insertBills.isEmpty()) {
					    	  //System.out.println("-----------------we are in if block -------------------");
					    	  
					    	  UserInfo user = userInfoRepository.findById(userId).get();
					    	  //System.out.println("--------------Users informations are----------------"+user);
					    	  String username=user.getUsername();
					    	  System.out.println("--------the username is-----"+ username);
					    	  eod.setUserName(username);
					    	  Set<String> distinctDenominations =  new HashSet<String>();
					    	   for(InsertBill bill : insertBills) {
				    		   distinctDenominations.add(bill.getAmount());
				    		   //System.out.println("------we are in for loop ending stage---------");
					    		   }
					    	   System.out.println("the "+username+"Denaminations are"+distinctDenominations);
					    	  int totalCount =0;
					    	  int sum = 0;
					    	  //Adding Distinct denominations in to a set like $1,$2,...
					    	   for(String a : distinctDenominations) {
				    		      int count = 0;
					    		   int product = 0;
					    		   //checking number of notes of same denomination present in the current date bill
					    		   for(InsertBill bill : insertBills) {
					    		     //System.out.println("--------we are in  find  the count for loop-----------");
					    			  if(a.equals(bill.getAmount())) {
					    				  count++;
					    				  }
					    			  }
					    		   System.out.println(count);
					    		   product = a.equals("$1")?1*count:a.equals("$2")?2*count:a.equals("$5")?5*count:a.equals("$10")?10*count:a.equals("$20")?20*count:
					    			   a.equals("$50")?50*count:a.equals("$100")?100*count:1*count;  
					    		   totalCount+= count;
					    		   sum+=product;
					    		   System.out.println("The "+username +"TotalAmount is "+sum);
				    		       eod.setTotalValue(sum);
						    	   //eod.setTotalCount(totalCount);
				    		      }
					    	   grandTotal+=sum;
					    	   eod.setGrandTotal(grandTotal);
					    	   
					    	   System.out.println("Total inserted bills of "+storeName+"is "+grandTotal);;
					    	   eodReports.add(eod);
					      }//if black ending
					    
					      
					}//first for loop ending
					
			return eodReports;
		}

	

	

	}










