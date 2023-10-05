package com.safesmart.safesmart.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BillValidatorResponse {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String billAcceptorNo;
	
	private String billAcceptorName;
	
	private String brandName;
	
	private String modelName;
	
	private String machineType;
	
	private String storageCapacity;
	
	private boolean active;

	public BillValidatorResponse(Long id, String billAcceptorNo, String billAcceptorName, String brandName,
			String modelName, String machineType, String storageCapacity, boolean active) {
		super();
		this.id = id;
		this.billAcceptorNo = billAcceptorNo;
		this.billAcceptorName = billAcceptorName;
		this.brandName = brandName;
		this.modelName = modelName;
		this.machineType = machineType;
		this.storageCapacity = storageCapacity;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillAcceptorNo() {
		return billAcceptorNo;
	}

	public void setBillAcceptorNo(String billAcceptorNo) {
		this.billAcceptorNo = billAcceptorNo;
	}

	public String getBillAcceptorName() {
		return billAcceptorName;
	}

	public void setBillAcceptorName(String billAcceptorName) {
		this.billAcceptorName = billAcceptorName;
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

	public String getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(String storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
