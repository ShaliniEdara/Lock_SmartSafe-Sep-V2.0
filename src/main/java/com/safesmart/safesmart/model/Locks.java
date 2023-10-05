package com.safesmart.safesmart.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "locks")
public class Locks {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="digital_lock_no")
    private String digitalLockNo;
	
	@Column(name="digital_lock_name")
	private String digitalLockName;
	
	@Column(name="brand_name")
	private String brandName;
	
	@Column(name="model_name")
	private String modelName;
	
	@Column(name="machine_type")
	private String machineType;
	
	private String connectors;
	
	private boolean active;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = StoreInfo.class)
	@JoinColumn(name="store_id")
	private StoreInfo storeInfo;

	
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="digital_lock_no")
	public String getDigitalLockNo() {
		return digitalLockNo;
	}
	@Column(name="digital_lock_no")
	public void setDigitalLockNo(String digitalLockNo) {
		this.digitalLockNo = digitalLockNo;
	}
	
	@Column(name="digital_lock_name")
	public String getDigitalLockName() {
		return digitalLockName;
	}
	@Column(name="digital_lock_name")
	public void setDigitalLockName(String digitalLockName) {
		this.digitalLockName = digitalLockName;
	}
	
	@Column(name="brand_name")
	public String getBrandName() {
		return brandName;
	}
	@Column(name="brand_name")
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name="model_name")
	public String getModelName() {
		return modelName;
	}
	@Column(name="model_name")
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name="machine_type")
	public String getMachineType() {
		return machineType;
	}
	
	@Column(name="machine_type")
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

	
	
	
}
