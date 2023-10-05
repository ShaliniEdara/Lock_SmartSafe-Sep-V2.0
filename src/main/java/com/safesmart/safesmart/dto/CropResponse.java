package com.safesmart.safesmart.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CropResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	
	private String cropName;
	
	private String description;
	
    private boolean status;
	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	

	public CropResponse(Long id, String cropName, String description, boolean status) {
		super();
		this.id = id;
		this.cropName = cropName;
		this.description = description;
		this.status = status;
	}
	

}
