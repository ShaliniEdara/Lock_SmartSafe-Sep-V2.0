package com.safesmart.safesmart.dto;

public class ChangedCurrencyDto {
	
	private String currency;
	private String currencytotal;
	private String depositedValue;
	private String changeNeeded;
	private String newcurrencytotal;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getChangeNeeded() {
		return changeNeeded;
	}
	public void setChangeNeeded(String changeNeeded) {
		this.changeNeeded = changeNeeded;
	}
	public String getDepositedValue() {
		return depositedValue;
	}
	public void setDepositedValue(String depositedValue) {
		this.depositedValue = depositedValue;
	}
	
	
	public String getCurrencytotal() {
		return currencytotal;
	}
	public void setCurrencytotal(String currencytotal) {
		this.currencytotal = currencytotal;
	}
	
	
	
	public String getNewcurrencytotal() {
		return newcurrencytotal;
	}
	public void setNewcurrencytotal(String newcurrencytotal) {
		this.newcurrencytotal = newcurrencytotal;
	}
	public ChangedCurrencyDto(String currency, String currencytotal, String depositedValue, String changeNeeded,
			String newcurrencytotal) {
		super();
		this.currency = currency;
		this.currencytotal = currencytotal;
		this.depositedValue = depositedValue;
		this.changeNeeded = changeNeeded;
		this.newcurrencytotal = newcurrencytotal;
	}
	@Override
	public String toString() {
		return "ChangedCurrencyDto [currency=" + currency + ", currencytotal=" + currencytotal + ", depositedValue="
				+ depositedValue + ", changeNeeded=" + changeNeeded + ", newcurrencytotal=" + newcurrencytotal + "]";
	}



}
