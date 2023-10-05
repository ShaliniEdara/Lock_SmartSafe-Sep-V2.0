package com.safesmart.safesmart.dto;


import java.util.List;

import com.safesmart.safesmart.model.Crop;

public class StoreInfoResponse {
	
	private Long id;

	private String storeName;

	private String corpStoreNo;

	private String serialNumber;

	private String address;

	private String bankName;

	private String accountNumber;

	private Double minimumBalance;

	private boolean configured;

	private String startTime;

	private String endTime;

	private List<Long> userIds;
	
	private List<Long> kioskIds;
	
	private List<Long> billValidatorIds;
	
	private List<Long> printerIds;
	
	private List<Long> lockIds;

	private int totalUsers;
	
	private int totalEmployess;
	
	private int totalManagers;
	
	private int totalAdmins;
	
	private int totalTruckers;
	
	private int totalShiftManagers;
	
	private int totalPrinters;
	
	private int totalKiosk;
	
	private int totalBillVallidator;
	
	private int totalLocks;
	
	private boolean status;
	
    private String userrName;
	
    private String kioskName;
	
	private String billvallidatorName;
	
	private String printerName;
	
	private String lockName;
	
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

	public Crop getCropName() {
		return cropName;
	}

	public void setCropName(Crop cropName) {
		this.cropName = cropName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCorpStoreNo() {
		return corpStoreNo;
	}

	public void setCorpStoreNo(String corpStoreNo) {
		this.corpStoreNo = corpStoreNo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public  List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	
	
	


	public List<Long> getKioskIds() {
		return kioskIds;
	}

	public void setKioskIds(List<Long> kioskIds) {
		this.kioskIds = kioskIds;
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Long> getBillValidatorIds() {
		return billValidatorIds;
	}

	public void setBillValidatorIds(List<Long> billValidatorIds) {
		this.billValidatorIds = billValidatorIds;
	}

	public List<Long> getPrinterIds() {
		return printerIds;
	}

	public void setPrinterIds(List<Long> printerIds) {
		this.printerIds = printerIds;
	}

	public List<Long> getLockIds() {
		return lockIds;
	}

	public void setLockIds(List<Long> lockIds) {
		this.lockIds = lockIds;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public StoreInfoResponse(Long id, String storeName, String corpStoreNo, String serialNumber) {
		super();
		this.id = id;
		this.storeName = storeName;
		this.corpStoreNo = corpStoreNo;
		this.serialNumber = serialNumber;
	}


	public StoreInfoResponse() {
		// TODO Auto-generated constructor stub
	}


	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public int getTotalEmployess() {
		return totalEmployess;
	}

	public void setTotalEmployess(int totalEmployess) {
		this.totalEmployess = totalEmployess;
	}

	public int getTotalManagers() {
		return totalManagers;
	}

	public void setTotalManagers(int totalManagers) {
		this.totalManagers = totalManagers;
	}

	public int getTotalAdmins() {
		return totalAdmins;
	}

	public void setTotalAdmins(int totalAdmins) {
		this.totalAdmins = totalAdmins;
	}

	public int getTotalTruckers() {
		return totalTruckers;
	}

	public void setTotalTruckers(int totalTruckers) {
		this.totalTruckers = totalTruckers;
	}

	public int getTotalShiftManagers() {
		return totalShiftManagers;
	}

	public void setTotalShiftManagers(int totalShiftManagers) {
		this.totalShiftManagers = totalShiftManagers;
	}

	public int getTotalPrinters() {
		return totalPrinters;
	}

	public void setTotalPrinters(int totalPrinters) {
		this.totalPrinters = totalPrinters;
	}

	public int getTotalKiosk() {
		return totalKiosk;
	}

	public void setTotalKiosk(int totalKiosk) {
		this.totalKiosk = totalKiosk;
	}

	public int getTotalBillVallidator() {
		return totalBillVallidator;
	}

	public void setTotalBillVallidator(int totalBillVallidator) {
		this.totalBillVallidator = totalBillVallidator;
	}

	public int getTotalLocks() {
		return totalLocks;
	}

	public void setTotalLocks(int totalLocks) {
		this.totalLocks = totalLocks;
	}

	public String getKioskName() {
		return kioskName;
	}

	public void setKioskName(String kioskName) {
		this.kioskName = kioskName;
	}

	public String getBillvallidatorName() {
		return billvallidatorName;
	}

	public void setBillvallidatorName(String billvallidatorName) {
		this.billvallidatorName = billvallidatorName;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public String getUserrName() {
		return userrName;
	}

	public void setUserrName(String userrName) {
		this.userrName = userrName;
	}

}
