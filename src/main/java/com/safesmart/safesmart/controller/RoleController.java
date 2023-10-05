package com.safesmart.safesmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safesmart.safesmart.dto.LocksRequest;
import com.safesmart.safesmart.dto.RoleDto;
import com.safesmart.safesmart.service.RoleService;

@RestController
@RequestMapping(value = "/role")
@CrossOrigin
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody RoleDto roleDto) {
		roleDto.validateRequiredAttributes();
		roleService.add(roleDto);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<RoleDto> findAll() {
		System.out.println("Coming into find all roles method ");
		return roleService.findAll();
	}
	@RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
	public void update(@PathVariable("roleId") Long id,@RequestBody RoleDto roleDto) {
		System.out.println("coming into role update");
		roleDto.setId(id);
		roleService.upDate(roleDto);
	}
	@RequestMapping(value="/{roleId}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("roleId") Long id) {
		roleService.toDelete(id);
	}
	
	@RequestMapping(value = "/addwebmodule", method = RequestMethod.POST)
	public void addWebModule(@RequestBody RoleDto roleDto) {
		roleService.addWebModule(roleDto);
	}
	
}
