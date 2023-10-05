package com.safesmart.safesmart.stand;

import java.util.List;

import com.safesmart.safesmart.dto.ReportDto;

public class standDto extends ReportDto {

	
	private String timeStamp;

	private List<standResponse> data;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<standResponse> getData() {
		return data;
	}

	public void setData(List<standResponse> data) {
		this.data = data;
	}
	
	
	
}
