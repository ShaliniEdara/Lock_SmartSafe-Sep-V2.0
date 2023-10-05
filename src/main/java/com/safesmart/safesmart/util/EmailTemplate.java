package com.safesmart.safesmart.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.safesmart.safesmart.dto.BillResponse;
import com.safesmart.safesmart.dto.EODReport;
import com.safesmart.safesmart.dto.EmployeeReportResponse;
import com.safesmart.safesmart.service.MailService;
import com.safesmart.safesmart.dto.ChangeRequestDto;
import com.safesmart.safesmart.model.TruckChangeRequest;

@Component
public class EmailTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailService mailService;

	@Autowired
	private Base64BasicEncryption base64BasicEncryption;

	public void createEndOfDayMail(EODReport eodReport) {
		System.out.println("email triggered");
		String body = "Bill Acceptor Receipt for SMART SAFE ";
		body += "\n";
		body += "Store Name : " + eodReport.getStoreInfoResponse().getStoreName() + "\n";

		body += "Corp Store Number: " + eodReport.getStoreInfoResponse().getCorpStoreNo() + "\n";
		body += "Serial number:  " + eodReport.getStoreInfoResponse().getSerialNumber() + "\n";

		body += "Printed on " +  LocalDate.now().minusDays(1) + "  " + eodReport.getStoreInfoResponse().getStartTime()
				+ "  to " + LocalDate.now().minusDays(1) + "  " + eodReport.getStoreInfoResponse().getEndTime();
	
		body += "\n";
		body += "\n";
		body += "\n";
		body += "\n";

		body += "By Person \n";
		body += "-------------------------------------------------------- \n";
		for (EmployeeReportResponse emp : eodReport.getData()) {
			body += "\n";
			body += emp.getName() + " \n";
			body += "\n";
			body += "Bills Accepted: " + "\n";
			body += "------------------------------------------------------------- \n";
			body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
			body += "------------------------------------------------------------- \n";
			int count = 0;
			int value = 0;
			for (BillResponse bill : emp.getData()) {
				body += bill.getCurrency() + "       \t \t  \t " + bill.getCount() + "     \t\t " + "$"+ bill.getValue() + "\n";
				
				count = count + bill.getCount();
				value = value + bill.getValue();
				body += "---------------------------------------------------------- \n";
			}

			body += "\n";

		}

		body += "\n";
		body += "\n";
		body += "Summary: \n";
		body += "-------------------------------------------------------------------------- \n";

		body += "Total Deposited:           " + eodReport.getTotalCount() + "           " + "$" +eodReport.getTotalValue() +  " \n";

		mailSender.send(mailService.constructEmail("End of Day Report", body.toString(), "gowripathi@gmail.com"));
	};

	
	public void createEndOfDayBankMail(EODReport eodReport) {
		System.out.println("Bank Email Triggered");
		String body = "Bill Acceptor Bank Receipt ";
		body += "\n";
		body += "Store Name : " + eodReport.getStoreInfoResponse().getStoreName() + "\n";

		body += "Corp Store Number: " + eodReport.getStoreInfoResponse().getCorpStoreNo() + "\n";
		body += "Serial number:  " + eodReport.getStoreInfoResponse().getSerialNumber() + "\n";

	//	body += "Printed on " +  LocalDate.now().minusDays(1) + "  " + eodReport.getStoreInfoResponse().getStartTime()
		//		+ "  to " + LocalDate.now().minusDays(1) + "  " + eodReport.getStoreInfoResponse().getEndTime();
	
		body += "\n";
		body += "\n";


//		for (EmployeeReportResponse emp : eodReport.getData()) {
//			body += "\n";
//			// body += emp.getName() + " \n";
//			body += "\n";
//			body += "Bills Accepted: " + "\n";
//			body += "------------------------------------------------------------- \n";
//			body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
//			body += "------------------------------------------------------------- \n";
//			int count = 0;
//			int value = 0;
//			
//		for (BillResponse bill : emp.getData()) {
//			body += bill.getCurrency() + "       \t \t  \t " + bill.getCount() + "     \t\t " + "$"+ bill.getValue() + "\n";
//			
//			count = count + bill.getCount();
//			value = value + bill.getValue();
//			body += "---------------------------------------------------------- \n";
//		}
//
//		body += "\n";
//		}
//	
		body += "Bills Accepted: " + "\n";
		body += "------------------------------------------------------------- \n";
		body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
		body += "------------------------------------------------------------- \n";
	

		
		body += "Total Deposited:           " + eodReport.getTotalCount() + "           " + "$" +eodReport.getTotalValue() +  " \n";

		body += "-------------------------------------------------------------------------- \n";

		mailSender.send(mailService.constructEmail("End of Day Bank Report", body.toString(), "gowripathi@gmail.com"));
	};
	
	public void createChangeRequestMail(ChangeRequestDto changeRequestDto) {
		System.out.println("Change Request Triggered");
		String body = "Change Request has been initiated by Smart Safe Manager to Armored car carrier. \r\n"
				+ "Below are the change request denominations to deliver.";
		body += "\n";
		//body += "Store Name : " + eodReport.getStoreInfoResponse().getStoreName() + "\n";

		//body += "Corp Store Number: " + changeRequestDto.getStoreInfoResponse().getCorpStoreNo() + "\n";
		//body += "Serial number:  " + changeRequestDto.getStoreInfoResponse().getSerialNumber() + "\n";

	//	body += "Printed on " +  LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getStartTime()
		//		+ "  to " + LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getEndTime();
	
		body += "\n";
		body += "\n";


//		for (EmployeeReportResponse emp : eodReport.getData()) {
//			body += "\n";
//			// body += emp.getName() + " \n";
//			body += "\n";
//			body += "Bills Accepted: " + "\n";
//			body += "------------------------------------------------------------- \n";
//			body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
//			body += "------------------------------------------------------------- \n";
//			int count = 0;
//			int value = 0;
//			
//		for (BillResponse bill : emp.getData()) {
//			body += bill.getCurrency() + "       \t \t  \t " + bill.getCount() + "     \t\t " + "$"+ bill.getValue() + "\n";
//			
//			count = count + bill.getCount();
//			value = value + bill.getValue();
//			body += "---------------------------------------------------------- \n";
//		}
//
//		body += "\n";
//		}
//	
		body += "Change Requested Accepted: " + "\n";
		body += "------------------------------------------------------------- \n";
		body += "Denominations " +      " \t \t  \t" + " Recommended Change "+" \n";
		body += "------------------------------------------------------------- \n";
		int changeReqtotal=changeRequestDto.getCents()+changeRequestDto.getNickels()+changeRequestDto.getDimes()+changeRequestDto.getQuarters()+changeRequestDto.getDen_1$()+changeRequestDto.getDen_5$()+changeRequestDto.getDen_10$()+changeRequestDto.getDen_20$();
		int count=0;
		if(changeRequestDto.getCents()!=0) {
			body += "Pennies :" +       "\t \t  \t"+changeRequestDto.getCents()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getNickels()!=0)
		{
			body += "Nickels :" +        "\t \t  \t"+changeRequestDto.getNickels()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getDimes()!=0) {
			body += "Dimes :" +          "\t \t  \t"+changeRequestDto.getDimes()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getQuarters()!=0) {
			body += "Quarters :" +       "\t \t  \t"+changeRequestDto.getQuarters()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getDen_1$()!=0) {
			body += "1$ :" +            "\t \t  \t"+changeRequestDto.getDen_1$()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getDen_5$()!=0) {
			body += "5$ :" +           "\t \t  \t"+changeRequestDto.getDen_5$()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getDen_10$()!=0) {
			body += "10$ :" +         "\t \t  \t"+changeRequestDto.getDen_10$()+"" + "\n";
			count++;
		}
		if(changeRequestDto.getDen_20$()!=0) {
			body += "20$ :" +        "\t \t  \t"+changeRequestDto.getDen_20$()+"" + "\n";
			count++;
		}
			
		
		body += "------------------------------------------------------------- \n";
	

		
		body += "Total Deposited: \t \t  \t"                 + "$" +changeReqtotal +  " \n";

		body += "-------------------------------------------------------------------------- \n";

		mailSender.send(mailService.constructEmail("Change Request", body.toString(), "gowripathi@gmail.com"));
	};
	
	public void confirmTruckchangeMail(TruckChangeRequest truckChangeRequest) {
		System.out.println("Confirm Change Request Triggered");
		String body = "Your Change Request has been confirmed by Armored car carrier. \r\n"
				+ "Below are the change request denominations delivered to manager.";
		//body += "Store Name : " + eodReport.getStoreInfoResponse().getStoreName() + "\n";

		//body += "Corp Store Number: " + changeRequestDto.getStoreInfoResponse().getCorpStoreNo() + "\n";
		//body += "Serial number:  " + changeRequestDto.getStoreInfoResponse().getSerialNumber() + "\n";

	//	body += "Printed on " +  LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getStartTime()
		//		+ "  to " + LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getEndTime();
	
		body += "\n";
		body += "\n";


//		for (EmployeeReportResponse emp : eodReport.getData()) {
//			body += "\n";
//			// body += emp.getName() + " \n";
//			body += "\n";
//			body += "Bills Accepted: " + "\n";
//			body += "------------------------------------------------------------- \n";
//			body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
//			body += "------------------------------------------------------------- \n";
//			int count = 0;
//			int value = 0;
//			
//		for (BillResponse bill : emp.getData()) {
//			body += bill.getCurrency() + "       \t \t  \t " + bill.getCount() + "     \t\t " + "$"+ bill.getValue() + "\n";
//			
//			count = count + bill.getCount();
//			value = value + bill.getValue();
//			body += "---------------------------------------------------------- \n";
//		}
//
//		body += "\n";
//		}
//	
		body += "Confirm Change Requested Accepted: " + "\n";
		body += "------------------------------------------------------------- \n";
		body += "Denominations " +        " \t \t \t" + " Recommended Change "+" \n";
		body += "------------------------------------------------------------- \n";
		int changeReqtotal=truckChangeRequest.getCents()+truckChangeRequest.getNickels()+truckChangeRequest.getDimes()+truckChangeRequest.getQuarters()+truckChangeRequest.getDen_1$()+truckChangeRequest.getDen_5$()+truckChangeRequest.getDen_10$()+truckChangeRequest.getDen_20$();
		int count=0;
		if(truckChangeRequest.getCents()!=0) {
			body += "Pennies :" +      "\t \t \t "+truckChangeRequest.getCents()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getNickels()!=0)
		{
			body += "Nickels :" +      "\t \t \t"+truckChangeRequest.getNickels()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDimes()!=0) {
			body += "Dimes :" +        "\t \t \t"+truckChangeRequest.getDimes()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getQuarters()!=0) {
			body += "Quarters :" +     "\t \t \t"+truckChangeRequest.getQuarters()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_1$()!=0) {
			body += "1$ :" + "\t \t \t "+truckChangeRequest.getDen_1$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_5$()!=0) {
			body += "5$ :" +"\t \t \t"+truckChangeRequest.getDen_5$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_10$()!=0) {
			body += "10$ :" +"\t \t \t"+truckChangeRequest.getDen_10$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_20$()!=0) {
			body += "20$ :" +"\t \t \t"+truckChangeRequest.getDen_20$()+"" + "\n";
			count++;
		}
			
		
		body += "------------------------------------------------------------- \n";
	

		
		body += "Total Deposited: \t \t \t" + "$" +changeReqtotal +  " \n";

		body += "-------------------------------------------------------------------------- \n";

		mailSender.send(mailService.constructEmail("Confirm Change Request", body.toString(), "gowripathi@gmail.com"));
	};
	public void cancelTruckchangeMail(TruckChangeRequest truckChangeRequest) {
		System.out.println("Cancel Change Request Triggered");
		String body = "Your Change Request has been cancel by Armored car carrier. \r\n"
				+ "Below are the change request denominations delivered to manager.";
		//body += "Store Name : " + eodReport.getStoreInfoResponse().getStoreName() + "\n";

		//body += "Corp Store Number: " + changeRequestDto.getStoreInfoResponse().getCorpStoreNo() + "\n";
		//body += "Serial number:  " + changeRequestDto.getStoreInfoResponse().getSerialNumber() + "\n";

	//	body += "Printed on " +  LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getStartTime()
		//		+ "  to " + LocalDate.now().minusDays(1) + "  " + changeRequestDto.getStoreInfoResponse().getEndTime();
	
		body += "\n";
		body += "\n";


//		for (EmployeeReportResponse emp : eodReport.getData()) {
//			body += "\n";
//			// body += emp.getName() + " \n";
//			body += "\n";
//			body += "Bills Accepted: " + "\n";
//			body += "------------------------------------------------------------- \n";
//			body += "Denomination " + "  " + " Qty Deposited " + "  " + " Value " + " \n";
//			body += "------------------------------------------------------------- \n";
//			int count = 0;
//			int value = 0;
//			
//		for (BillResponse bill : emp.getData()) {
//			body += bill.getCurrency() + "       \t \t  \t " + bill.getCount() + "     \t\t " + "$"+ bill.getValue() + "\n";
//			
//			count = count + bill.getCount();
//			value = value + bill.getValue();
//			body += "---------------------------------------------------------- \n";
//		}
//
//		body += "\n";
//		}
//	
		body += "Cancel Change Request" + "\n";
		body += "------------------------------------------------------------- \n";
		body += "Denominations " +        " \t \t \t" + " Recommended Change "+" \n";
		body += "------------------------------------------------------------- \n";
		int changeReqtotal=truckChangeRequest.getCents()+truckChangeRequest.getNickels()+truckChangeRequest.getDimes()+truckChangeRequest.getQuarters()+truckChangeRequest.getDen_1$()+truckChangeRequest.getDen_5$()+truckChangeRequest.getDen_10$()+truckChangeRequest.getDen_20$();
		int count=0;
		if(truckChangeRequest.getCents()!=0) {
			body += "Pennies :" +      "\t \t \t "+truckChangeRequest.getCents()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getNickels()!=0)
		{
			body += "Nickels :" +      "\t \t \t"+truckChangeRequest.getNickels()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDimes()!=0) {
			body += "Dimes :" +        "\t \t \t"+truckChangeRequest.getDimes()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getQuarters()!=0) {
			body += "Quarters :" +     "\t \t \t"+truckChangeRequest.getQuarters()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_1$()!=0) {
			body += "1$ :" + "\t \t \t "+truckChangeRequest.getDen_1$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_5$()!=0) {
			body += "5$ :" +"\t \t \t"+truckChangeRequest.getDen_5$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_10$()!=0) {
			body += "10$ :" +"\t \t \t"+truckChangeRequest.getDen_10$()+"" + "\n";
			count++;
		}
		if(truckChangeRequest.getDen_20$()!=0) {
			body += "20$ :" +"\t \t \t"+truckChangeRequest.getDen_20$()+"" + "\n";
			count++;
		}
			
		
		body += "------------------------------------------------------------- \n";
	

		
		body += "Total Deposited: \t \t \t" + "$" +changeReqtotal +  " \n";

		body += "-------------------------------------------------------------------------- \n";

		mailSender.send(mailService.constructEmail("Cancel Change Request", body.toString(), "gowripathi@gmail.com"));
	};
	
}
