package com.safesmart.safesmart.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;

import com.safesmart.safesmart.model.StoreInfo;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.repository.UserInfoRepository;

public class UserInfoResponse {
	
	@Autowired
	UserInfoRepository userInfoRepository;

	private Long id;

	private String username;

	private String password;

	private String role;
	
	private String storeInfo;
	
	private String address;

	private boolean active;

	private String firstName;
	private String lastName;
	private String email;
	private String mobile;

	private LocalDate create_time;
	private LocalDateTime lastLoginTime;




	@Override
	public String toString() {
		return "UserInfoResponse [userInfoRepository=" + userInfoRepository + ", id=" + id + ", username=" + username
				+ ", password=" + password + ", role=" + role + ", storeInfo=" + storeInfo + ", address=" + address
				+ ", active=" + active + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", create_time=" + create_time + ", lastLoginTime=" + lastLoginTime
				+ ", passLenght=" + passLenght + "]";
	}
	
	

	public LocalDate getCreate_time() {
		return create_time;
	}

	public void setCreate_time(LocalDate create_time) {
		this.create_time = create_time;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}



	private String passLenght;
	
	public String getPassLenght() {
		return passLenght;
	}

	public void setPassLenght(String passLenght) {
		this.passLenght = passLenght;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserInfoResponse(Long id, String username, String password, String role, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
	}
	
	public UserInfoResponse(Long id, String username, String password, String storeInfo, String address, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.storeInfo = storeInfo;
		this.address = address;
		this.active = active;
	}

	
	public UserInfoResponse(Long id, String username, String password, String storeInfo, String address, String role, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.storeInfo = storeInfo;
		this.address = address;
		this.role = role;
		this.active = active;
	}

	public UserInfoResponse(Long id, String username, String password, String role, boolean active,String firstName,String lastName,String email,String mobile,String storeInfo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.storeInfo= storeInfo;
	}
	
	public UserInfoResponse(Long id, String username, String password, String role, boolean active,String firstName,String lastName,String email,String mobile) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public UserInfoResponse () {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

	public String getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(String storeInfo) {
		this.storeInfo = storeInfo;
	}
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
