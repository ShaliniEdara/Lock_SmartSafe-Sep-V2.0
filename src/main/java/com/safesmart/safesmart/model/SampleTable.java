package com.safesmart.safesmart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sample_table")
public class SampleTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(name="anji")
	private String anJi;

	
	
	public SampleTable() {
		super();
		// TODO Auto-generated constructor stub
	}







	public SampleTable(Long id, String name, String anJi) {
		super();
		this.id = id;
		this.name = name;
		this.anJi = anJi;
	}







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







	public String getAnJi() {
		return anJi;
	}







	public void setAnJi(String anJi) {
		this.anJi = anJi;
	}


	
	
	
}
