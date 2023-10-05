package com.safesmart.safesmart.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safesmart.safesmart.dto.RoleDto;
import com.safesmart.safesmart.model.Role;

@Component
public class RoleBuilder {
	public RoleDto toDto(Role role) {

		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setDescription(role.getDescription());
		dto.setName(role.getName());
		dto.setFeatures(role.getFeatures());
		dto.setWebModule(role.getWebModule());
		return dto;

	}

	public List<RoleDto> toDtoList(List<Role> roles) {

		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role role : roles) {
			dtos.add(toDto(role));
		}
		return dtos;
	}
	
	public Role toUpdate(RoleDto roleDto) {

		Role role = new Role(); 
		role.setId(roleDto.getId());
		role.setFeatures(roleDto.getFeatures());
		role.setWebModule(roleDto.getWebModule());
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());
		System.out.println(roleDto.getDescription());

		return role;
	}

	public Role toModel(RoleDto roleDto) {

		Role role = new Role();
		role.setId(roleDto.getId());
		role.setFeatures(roleDto.getFeatures());
		role.setWebModule(roleDto.getWebModule());
		role.setName(roleDto.getName());
		role.setDescription(roleDto.getDescription());

		return role;
	}
}
