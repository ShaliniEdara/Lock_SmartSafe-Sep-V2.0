package com.safesmart.safesmart.dto;

import java.util.List;

import javax.persistence.Convert;

import com.safesmart.safesmart.common.CommonException;
import com.safesmart.safesmart.common.CommonExceptionMessage;
import com.safesmart.safesmart.model.StringListConverter;

public class RoleDto {

	private Long id;

	private String name;

	private String description;
	
	private List<String> webModule;

	private List<String> features;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	



	public List<String> getWebModule() {
		return webModule;
	}

	public void setWebModule(List<String> webModule) {
		this.webModule = webModule;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public void validateRequiredAttributes() {
	if (name.isEmpty()) {
		throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "name");
		}		
		if (description.isEmpty()) {
			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "description");
		}
//		if (webModule.isEmpty()) {
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "webModule");
//		}
//		if (features.isEmpty()) {
//			throw CommonException.CreateException(CommonExceptionMessage.REQUIRED_ATTRIBUTE, "features");
//		}

}
}
