package com.safesmart.safesmart.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "insert_bill")
public class InsertBill {

	private Long id;

	private String amount;

	private UserInfo user;

	private LocalDate createdOn;

	private LocalDateTime dateTime;

	private String transactionNumber;

	@Override
	public String toString() {
		return "InsertBill [id=" + id + ", amount=" + amount + ", user=" + user + ", createdOn=" + createdOn
				+ ", dateTime=" + dateTime + ", transactionNumber=" + transactionNumber + "]";
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@ManyToOne(optional = true)
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Column(name="created_on")
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	@Column(name="created_on")
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name="transaction_number")
	public String getTransactionNumber() {
		return transactionNumber;
	}
	@Column(name="transaction_number")
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Column(name="date_time")
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	@Column(name="date_time")
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
