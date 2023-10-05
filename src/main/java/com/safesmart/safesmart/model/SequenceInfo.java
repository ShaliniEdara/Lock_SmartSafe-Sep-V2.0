package com.safesmart.safesmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequence_info")
public class SequenceInfo {

	private Long id;

	private String name;

	private int value;

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String increment() {
		this.value = value + 1;
		return String.format("%06d", value);
	}
	
	public String formatedValue() {
		return String.format("%06d", value);
	}
	
	

}
