package com.safesmart.safesmart.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PrinterResponse {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private String printerNo;
	
	private String printerName;
	
	private String brandName;
	
	private String modelName;
	
	private String machineType;
	
	private String printCapacity;
	
	private boolean active;

	public PrinterResponse(Long id, String printerNo, String printerName, String brandName, String modelName,
			String machineType, String printCapacity, boolean active) {
		super();
		this.id = id;
		this.printerNo = printerNo;
		this.printerName = printerName;
		this.brandName = brandName;
		this.modelName = modelName;
		this.machineType = machineType;
		this.printCapacity = printCapacity;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrinterNo() {
		return printerNo;
	}

	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getPrintCapacity() {
		return printCapacity;
	}

	public void setPrintCapacity(String printCapacity) {
		this.printCapacity = printCapacity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
