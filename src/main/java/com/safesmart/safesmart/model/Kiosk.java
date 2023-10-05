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
@Table(name = "kiosk")
public class Kiosk {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@Column(name="kiosk_id")
	private String kioskId ;
	
	@Column(name="kiosk_name")
	private String kioskName;
		
	@Column(name="brand_name")
	private String brandName;
	
	@Column(name="model_name")
	private String modelName;
	
	private String cpu;
	
	private String hdd;
	
	@Column(name="ram_memory")
	private String ramMemory;
	
	@Column(name="screen_size")
	private String screenSize;
	
	@Column(unique=true, name="ip_address")
	private String ipAddress;
	
	@Column(name="mac_address")
	private String macAddress;
	
	private String port;
	
	private boolean active;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = StoreInfo.class)
	@JoinColumn(name="store_id")
	private StoreInfo storeinfok;
	
	


	public StoreInfo getStoreinfok() {
		return storeinfok;
	}
	public void setStoreinfok(StoreInfo storeinfok) {
		this.storeinfok = storeinfok;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="kiosk_id")
	public String getKioskId() {
		return kioskId;
	}
	@Column(name="kiosk_id")
	public void setKioskId(String kioskId) {
		this.kioskId = kioskId;
	}

	@Column(name="kiosk_name")
	public String getKioskName() {
		return kioskName;
	}
	@Column(name="kiosk_name")
	public void setKioskName(String kioskName) {
		this.kioskName = kioskName;
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

	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getHdd() {
		return hdd;
	}
	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	@Column(name="ram_memory")
	public String getRamMemory() {
		return ramMemory;
	}
	@Column(name="ram_memory")
	public void setRamMemory(String ramMemory) {
		this.ramMemory = ramMemory;
	}

	@Column(name="screen_size")
	public String getScreenSize() {
		return screenSize;
	}
	@Column(name="screen_size")
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	@Column(name="ip_address")
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name="mac_address")
	public String getMacAddress() {
		return macAddress;
	}
	@Column(name="mac_address")
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public void add(Long id2) {
		// TODO Auto-generated method stub
		
	}

	
	

}
