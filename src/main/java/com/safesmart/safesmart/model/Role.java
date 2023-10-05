package com.safesmart.safesmart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {

	private Long id;

	private String name;

	private String description;
	
	private List<String> webModule;

	private List<String> features;

	private List<UserInfo> users = new ArrayList<UserInfo>();

	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Convert(converter = StringListConverter.class)
    @Column(name="web_module")
	public List<String> getWebModule() {
		return webModule;
	}
	
	@Convert(converter = StringListConverter.class)
    @Column(name="web_module")
	public void setWebModule(List<String> webModule) {
		this.webModule = webModule;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
	

	@Convert(converter = StringListConverter.class)
	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

}
