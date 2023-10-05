package com.safesmart.safesmart.dto;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;

public class UserInfoRequest {

	private Long id;

	private String username;

	private String password;

	private String role;

	private boolean active;

	private String feature;
	
	private String web;
	
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String passLength;
	
	public String getPassLength() {
		return passLength;
	}

	public void setPassLength(String passLength) {
		this.passLength = passLength;
	}



	@Override
	public String toString() {
		return "UserInfoRequest [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", active=" + active + ", feature=" + feature + ", web=" + web + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", passLength=" + passLength
				+ ", loggedUserId=" + loggedUserId + "]";
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

	// admin or manager can create users
	private Long loggedUserId;

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}
	
	

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Long getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Long loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	public void validateRequiredAttributes() {
//		if (firstName.isEmpty()) {
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "fisrtname");
//		}		
//		if (lastName.isEmpty()) {
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "lastname");
//		}
//		if (mobile.isEmpty()) {
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "mobile");
//		}
//		 
	
		if (username.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Username");
		}
		if (password.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Password");
		}
		if (role.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Role");
		}
//		if(!email.contains(".com"))
//		{
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Email");
//		}
		String regex = "[0-9]+";
		Pattern p = Pattern.compile(regex);
//		if(passLength.equals("six")) {
//			System.out.println("in pin error");
//			if (password.length() != 6 || !p.matcher(password).matches()) {
//				throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_SIX_PIN);
//			}	
//		}
//		else {
//		if (password.length() != 4 || !p.matcher(password).matches()) {
//			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_PIN);
//		}
//		}

	}

	public void validateLoginRequired() {
		
		if (password==null) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Password");
		}
		
		if (password.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Password");
		}
	

		String regex = "[0-9]+";
		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
//		if (password.length() != 4 || !p.matcher(password).matches()) {
//			throw CommonException.CreateException(CommonExceptionMessage.VALIDATE_PIN);
//		}

		if (feature.isEmpty()) {
			System.out.println("feature errror");
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "Feautre");
		}
		

	}
	
	
	
	
}
	