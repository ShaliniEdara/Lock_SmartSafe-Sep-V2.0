package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.KioskResponse;
import com.safesmart.safesmart.dto.RoleDto;
import com.safesmart.safesmart.dto.RolesDto;
import com.safesmart.safesmart.dto.StoreInfoResponse;
import com.safesmart.safesmart.dto.UserInfoRequest;
import com.safesmart.safesmart.dto.UserInfoResponse;
import com.safesmart.safesmart.model.UserInfo;
import com.safesmart.safesmart.service.UserService;
import com.safesmart.safesmart.util.Base64BasicEncryption;

@RestController
@RequestMapping(value = "/userInfo/")
@CrossOrigin
public class UserInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private Base64BasicEncryption passwordEncrypt;
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody UserInfoRequest userInfoRequest) {
		userInfoRequest.validateRequiredAttributes();
		userService.add(userInfoRequest);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserInfoResponse> findAllEmployee() {
		return userService.findAllUser();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteByUser(@PathVariable("userId") Long userId) {
		userService.deleteByUser(userId);
	}
	
	@RequestMapping(value = "/basedOnDays/{days}", method = RequestMethod.GET)
	public void deleteByUserD(@PathVariable("days") Long days) {
		userService.deleteByUserD(days);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public UserInfoResponse UpdateUserForm(@PathVariable("userId") Long userId) {
		System.out.println("coming into updateuserform");
		
		return userService.updateUserForm(userId);
			
	}

	@RequestMapping(value = "/loginKiosk", method = RequestMethod.POST)
	public UserInfoResponse doLoginKiosk(@RequestBody UserInfoRequest infoRequest) {
		System.out.println("coming here 2");	
		infoRequest.validateLoginRequired();
		return userService.doLoginkiosk(infoRequest);
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserInfoResponse doLogin(@RequestBody UserInfoRequest infoRequest) {
		System.out.println("coming here 2");
		infoRequest.validateLoginRequired();
		System.out.println("coming here 2");
		return userService.doLogin(infoRequest);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserInfoRequest userInfoRequest) {
		userInfoRequest.setId(userId);
		System.out.println("coming into update");
		userService.updateUser(userInfoRequest);
	}

	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public List<UserInfoResponse> getUserPaginations(@RequestParam("page") int page, @RequestParam("size") int size) {
		return userService.getUserPaginations(page, size);
	}

	@RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
	public List<UserInfoResponse> findUsersByRole(@PathVariable("role") String role) {
		return userService.findUsersByRole(role);
	}
	
	@RequestMapping(value = "/store/{storeName}", method = RequestMethod.GET)
	public List<UserInfoResponse> findUsersByStore(@PathVariable("storeName") String storeName) {
		return userService.findUsersByStore(storeName);
	}
	
	@RequestMapping(value = "/store/{storeName}/{role}", method = RequestMethod.GET)
	public List<UserInfoResponse> findUsersByStore(@PathVariable("storeName") String storeName,@PathVariable("role") String role) {
		System.out.println("storeemployee");
		return userService.findUsersByStore(storeName,role);
	}
	
	@RequestMapping(value = "/role/{role}/unassignedusers", method = RequestMethod.GET)
	public List<UserInfoResponse> findunassignedUsersByRole(@PathVariable("role") String role) {
		return userService.findunassignedUsersByRole(role);
	}

	@RequestMapping(value = "/users/roles", method = RequestMethod.POST)
	public List<UserInfoResponse> findUserbyRoles(@RequestBody RolesDto rolesDto) {
		return userService.findUserbyRoles(rolesDto);
	}
	
	@RequestMapping(value = "/users/roles/{storeName}", method = RequestMethod.POST)
	public List<UserInfoResponse> findUserbyRoles(@RequestBody RolesDto rolesDto,@PathVariable("storeName") String storeName) {
		return userService.findUserbyRolesss(rolesDto,storeName);
	}

	@RequestMapping(value = "/unassigned/users/roles", method = RequestMethod.POST)
	public List<UserInfoResponse> findUnassignedUserbyRoles(@RequestBody RolesDto rolesDto) {
		return userService.findUnassignedUserbyRoles(rolesDto);
	}
	
	@RequestMapping(value = "/all/roles", method = RequestMethod.GET)
	public List<RoleDto> findAllRoles() {
		return userService.findAllRoles();
	}

	@RequestMapping(value = "/promoteUser/{userId}", method = RequestMethod.POST)
	public void promoteUser(@PathVariable("userId") Long userId, @RequestBody RoleDto roleDto) {
		userService.promoteUser(userId, roleDto);
	}
	
	//change password
	@RequestMapping(value = "/changePassword/{oldPassword}/{newPassword}", method = RequestMethod.POST)
	public void changePassword(@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword) {
	
		System.out.println("coming here 4");
		userService.changePassword(oldPassword,newPassword);
	}
	
	
	@RequestMapping(value = "usertokiosk/{userId}", method = RequestMethod.GET)
	public List<KioskResponse> usertokiosk(@PathVariable("userId") Long userId) {
		return userService.usertokiosk(userId);

	}
	
}
