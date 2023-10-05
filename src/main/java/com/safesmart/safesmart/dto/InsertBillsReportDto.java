package com.safesmart.safesmart.dto;

import java.util.Collection;

public class InsertBillsReportDto extends ReportDto {

	private String timeStamp;

	private Collection<BillResponse> data;

	public Collection<BillResponse> getData() {
		return data;
	}

	public void setData(Collection<BillResponse> data) {
		this.data = data;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
