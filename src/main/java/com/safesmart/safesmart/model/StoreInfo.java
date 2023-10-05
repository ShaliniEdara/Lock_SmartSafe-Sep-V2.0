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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "store_info")
public class StoreInfo {

	private Long id;

	private String storeName;

	private String corpStoreNo;

	private String serialNumber;

	private String address;

	private String bankName;

	private String accountNumber;

	private Double minimumBalance;
	
	private boolean configured;
	
	private boolean status;

	
	private LocalTime startTime;
	
	private LocalTime endTime;

	private List<UserInfo> users;
	
	private List<Kiosk> kiosk;
	
	private List<BillValidator> billValidator;
	 
	private List<Locks> locks;
	
	private List<Printer> printer;
	
    private String cityName;
	
	private String zipCode;
	
	private String stateName;
	
	private String streetName;
	
	private Crop  cropName;
	
	

    public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	
	@ManyToOne
	@JoinColumn(name="crop_name")
	public Crop getCropName() {
		return cropName;
	}
	@JoinColumn(name="crop_name")
	public void setCropName(Crop cropName) {
		this.cropName = cropName;
	}

	@JsonIgnore
	@OneToMany(targetEntity=Locks.class,cascade = CascadeType.ALL, mappedBy="storeInfo")
	public List<Locks> getLocks() {
		return locks;
	}

	public void setLocks(List<Locks> locks) {
		this.locks = locks;
	}

	@JsonIgnore
	@OneToMany(targetEntity=Printer.class,cascade = CascadeType.ALL, mappedBy="storeinfop")
	public List<Printer> getPrinter() {
		return printer;
	}

	public void setPrinter(List<Printer> printer) {
		this.printer = printer;
	}
	
	
	
	@JsonIgnore
	@OneToMany(targetEntity=Kiosk.class,cascade = CascadeType.ALL, mappedBy="storeinfok")
	public List<Kiosk> getKiosk() {
		return kiosk;
	}

	public void setKiosk(List<Kiosk> kiosk) {
		this.kiosk = kiosk;
	}
	
	@JsonIgnore
	@OneToMany(targetEntity=BillValidator.class,cascade = CascadeType.ALL, mappedBy="storeinfob")
	public List<BillValidator> getBillValidator() {
		return billValidator;
	}

	public void setBillValidator(List<BillValidator> billValidator) {
		this.billValidator = billValidator;
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="store_name")
	public String getStoreName() {
		return storeName;
	}
    @Column(name="store_name")
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

    @Column(name="corp_store_no")
	public String getCorpStoreNo() {
		return corpStoreNo;
	}
    @Column(name="corp_store_no")
	public void setCorpStoreNo(String corpStoreNo) {
		this.corpStoreNo = corpStoreNo;
	}

    @Column(name="serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

    @Column(name="serial_number")
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    @Column(name="bank_Name")
	public String getBankName() {
		return bankName;
	}
    @Column(name="bank_Name")
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

    @Column(name="account_number")
	public String getAccountNumber() {
		return accountNumber;
	}
    @Column(name="account_number")
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

    @Column(name="minimum_balance")
	public Double getMinimumBalance() {
		return minimumBalance;
	}
    @Column(name="minimum_balance")
	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="storeInfo" )
	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

    @Column(name="start_time")
	public LocalTime getStartTime() {
		return startTime;
	}
    @Column(name="start_time")
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

    @Column(name="end_time")
	public LocalTime getEndTime() {
		return endTime;
	}

    @Column(name="end_time")
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	
	
	

		public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StoreInfo [id=" + id + ", storeName=" + storeName + ", corpStoreNo=" + corpStoreNo + ", serialNumber="
				+ serialNumber + ", address=" + address + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", minimumBalance=" + minimumBalance + ", configured=" + configured + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", users=" + users + ", kiosk=" + kiosk
				+ ", billValidator=" + billValidator + ", locks=" + locks + ", printer=" + printer + ", cityName="
				+ cityName + ", zipCode=" + zipCode + ", stateName=" + stateName + ", streetName=" + streetName
				+ ", cropName=" + cropName + "]";
	}

		

}
