 package com.safesmart.safesmart.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity		
@Table(name = "crop")
public class Crop {
	@Id
	private Long id;
	
	@Column(name="crop_name")
	private String cropName;
	

	@Column(name="description")
	private String description;
	

	@Column(name="status")
	private boolean status;
	 
//	private StoreInfo storeInfo; 
//	
//	@ManyToOne
//	@JoinColumn(name="store_info_id")
//    public StoreInfo getStoreInfo() {
//		return storeInfo;
//	}
//	@JoinColumn(name="store_info_id")
//	public void setStoreInfo(StoreInfo storeInfo) {
//		this.storeInfo = storeInfo;
//	}

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

	@Override
	public String toString() {
		return "Crop [id=" + id + ", cropName=" + cropName + ", description=" + description + ", status=" + status
				+ "]";
	}
	
	

	
}
