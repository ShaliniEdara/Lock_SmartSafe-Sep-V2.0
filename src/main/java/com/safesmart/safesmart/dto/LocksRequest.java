package com.safesmart.safesmart.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;

public class LocksRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    private String digitalLockNo;
	
	private String digitalLockName;
	
	private String brandName;
	
	private String modelName;
	
	private String machineType;
	
	private String connectors;
	
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDigitalLockNo() {
		return digitalLockNo;
	}

	public void setDigitalLockNo(String digitalLockNo) {
		this.digitalLockNo = digitalLockNo;
	}

	public String getDigitalLockName() {
		return digitalLockName;
	}

	public void setDigitalLockName(String digitalLockName) {
		this.digitalLockName = digitalLockName;
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

	public String getConnectors() {
		return connectors;
	}

	public void setConnectors(String connectors) {
		this.connectors = connectors;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void validateRequiredAttributes() {

		if (StringUtils.isEmpty(digitalLockName)) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "DigitalLockName");
		}
	}
}
