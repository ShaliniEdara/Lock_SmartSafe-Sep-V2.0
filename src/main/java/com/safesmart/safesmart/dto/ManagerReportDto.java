package com.safesmart.safesmart.dto;

import java.util.ArrayList;
import java.util.List;

public class ManagerReportDto extends ReportDto {

	private List<EmployeeReportResponse> employeeReportResponses = new ArrayList<EmployeeReportResponse>();

	public List<EmployeeReportResponse> getEmployeeReportResponses() {
		return employeeReportResponses;
	}

	public void setEmployeeReportResponses(List<EmployeeReportResponse> employeeReportResponses) {
		this.employeeReportResponses = employeeReportResponses;
	}

}
