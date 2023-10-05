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
@Table(name = "bill_validator")
public class BillValidator {

	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="bill_acceptor_no")
	private String billAcceptorNo;
	
	@Column(name="bill_acceptor_name")
	private String billAcceptorName;
	
	@Column(name="brand_name")
	private String brandName;
	
	@Column(name="model_name")
	private String modelName;
	
	@Column(name="machine_type")
	private String machineType;
	
	@Column(name="storage_capacity")
	private String storageCapacity;
	
	private boolean active;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = StoreInfo.class)
	@JoinColumn(name="store_id")
	private StoreInfo storeinfob;
	
	

	public StoreInfo getStoreinfob() {
		return storeinfob;
	}

	public void setStoreinfob(StoreInfo storeinfob) {
		this.storeinfob = storeinfob;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="bill_acceptor_no")
	public String getBillAcceptorNo() {
		return billAcceptorNo;
	}
	@Column(name="bill_acceptor_no")
	public void setBillAcceptorNo(String billAcceptorNo) {
		this.billAcceptorNo = billAcceptorNo;
	}
	
	@Column(name="bill_acceptor_name")
	public String getBillAcceptorName() {
		return billAcceptorName;
	}
	@Column(name="bill_acceptor_name")
	public void setBillAcceptorName(String billAcceptorName) {
		this.billAcceptorName = billAcceptorName;
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

	@Column(name="storage_capacity")
	public String getStorageCapacity() {
		return storageCapacity;
	}
	@Column(name="storage_capacity")
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
