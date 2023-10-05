package com.safesmart.safesmart.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.util.DateUtil;

public class DateRangedto {

	private String startDate;

	private String endDate;
	
	private String startTime;
	
	private String endTime;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void validateRequest() {

		if (startDate == null) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_FROMDATE);
		}

		if (endDate == null) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_TODATE);
		}

		LocalDate stDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (stDate.isAfter(eDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

	}
	
	public void validateRequest1() {

		if (startDate == null) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_FROMDATE);
		}

		if (endDate == null) {
			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_TODATE);
		}

		String stDate1 = DateUtil.convertToStringDateFormat(startDate.substring(0, 10));
		stDate1=	stDate1+" "+startTime;
		
		String enDate1 = DateUtil.convertToStringDateFormat(endDate.substring(0, 10));
		enDate1=	enDate1+" "+endTime;
		
		LocalDateTime stDate = LocalDateTime.parse(stDate1, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		LocalDateTime eDate = LocalDateTime.parse(enDate1, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		if (stDate.isAfter(eDate)) {
			throw new RuntimeException("Start Date should be less than the End Date");
		}

	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
