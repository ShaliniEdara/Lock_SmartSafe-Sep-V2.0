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
@Table(name = "printer")
public class Printer {

	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="printer_no")
    private String printerNo;
	
	@Column(name="printer_name")
	private String printerName;
	
	@Column(name="brand_name")
	private String brandName;
	
	@Column(name="model_name")
	private String modelName;
	
	@Column(name="machine_type")
	private String machineType;
	
	@Column(name="print_capacity")
	private String printCapacity;
	
	private boolean active;
	
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = StoreInfo.class)
	@JoinColumn(name="store_id")
	private StoreInfo storeinfop;


	public StoreInfo getStoreinfop() {
		return storeinfop;
	}

	public void setStoreinfop(StoreInfo storeinfop) {
		this.storeinfop = storeinfop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="printer_no")
	public String getPrinterNo() {
		return printerNo;
	}
	@Column(name="printer_no")
	public void setPrinterNo(String printerNo) {
		this.printerNo = printerNo;
	}

	@Column(name="printer_name")
	public String getPrinterName() {
		return printerName;
	}
	@Column(name="printer_name")
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
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

	@Column(name="print_capacity")
	public String getPrintCapacity() {
		return printCapacity;
	}
	@Column(name="print_capacity")
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
