package com.safesmart.safesmart.dto;

import java.util.List;

public class EODReport extends ReportDto {

	private String timeStamp;

	private List<EmployeeReportResponse> data;
	
	private int totalCount;
	
	private int totalValue;
	
	private int grandTotal;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<EmployeeReportResponse> getData() {
		return data;
	}

	public void setData(List<EmployeeReportResponse> data) {
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

	
}
