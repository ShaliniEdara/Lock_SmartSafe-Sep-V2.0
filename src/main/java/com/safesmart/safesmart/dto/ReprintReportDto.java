package com.safesmart.safesmart.dto;

import java.util.Collection;

public class ReprintReportDto extends ReportDto {

	private Collection<BillResponse> data;

	public Collection<BillResponse> getData() {
		return data;
	}

	public void setData(Collection<BillResponse> data) {
		this.data = data;
	}

}
