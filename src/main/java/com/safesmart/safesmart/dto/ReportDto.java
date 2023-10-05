package com.safesmart.safesmart.dto;

public class ReportDto {

	private String userName;

	private String reportName;

	private StoreInfoResponse storeInfoResponse;
	
	private String timeStamp;
	

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public StoreInfoResponse getStoreInfoResponse() {
		return storeInfoResponse;
	}

	public void setStoreInfoResponse(StoreInfoResponse storeInfoResponse) {
		this.storeInfoResponse = storeInfoResponse;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
