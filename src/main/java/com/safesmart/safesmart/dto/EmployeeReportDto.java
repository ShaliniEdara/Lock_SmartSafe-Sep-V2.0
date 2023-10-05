package com.safesmart.safesmart.dto;

import java.util.List;

public class EmployeeReportDto extends ReportDto {
	private String timeStamp;

	private List<EmployeeReportResponse> data;

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

}
