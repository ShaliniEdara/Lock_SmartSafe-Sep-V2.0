package com.safesmart.safesmart.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
@Table(name = "locks_info")
public class LocksInfo {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	

	@Column(name="lock_type")
	private String lockType; 
	
	@Column(name="lock_time")
	private LocalDateTime lockTime;
	
	@Column(name="lock_status")
	private String lockStatus;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = UserInfo.class)
	@JoinColumn(name="user_id")
	private UserInfo users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	@Column(name="lock_type")
	public String getLockType() {
		return lockType;
	}
	@Column(name="lock_type")
	public void setLockType(String lockType) {
		this.lockType = lockType;
	}
	
	@Column(name="lock_time")
	public LocalDateTime getLockTime() {
		return lockTime;
	}
	@Column(name="lock_time")
	public void setLockTime(LocalDateTime lockTime) {
		this.lockTime = lockTime;
	}
	
	@Column(name="lock_status")
	public String getLockStatus() {
		return lockStatus;
	}
	@Column(name="lock_status")
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public UserInfo getUsers() {
		return users;
	}

	public void setUsers(UserInfo users) {
		this.users = users;
	}
	
	
}
