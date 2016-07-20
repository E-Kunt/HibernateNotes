package com.ekunt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

@Entity
@IdClass(Wife.class)
public class Wife {
	private int id;
	private String name;
	private Husband husband;
	
	public Wife() {
		
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Id
	public String getName() {
		return name;
	}
	
	@OneToOne(mappedBy="wife")
	public Husband getHusband() {
		return husband;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}


	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	

}
