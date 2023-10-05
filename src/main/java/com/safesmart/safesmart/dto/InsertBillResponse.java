package com.safesmart.safesmart.dto;

import java.time.LocalDate;

public class InsertBillResponse {
	private Long id;

	private String amount;

	private String user;

	private LocalDate createdOn;

	private String transactionNumber;

	private int count;

	private int sum;

	public InsertBillResponse(String transactionNumber) {
		this.transactionNumber = transactionNumber;

	}

	public InsertBillResponse() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int calculateSum(int amount) {
		this.sum=amount*count;
		return sum;
	}

}
