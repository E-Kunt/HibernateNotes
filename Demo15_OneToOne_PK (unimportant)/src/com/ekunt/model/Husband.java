package com.ekunt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Husband {
	private int id;
	private String name;
	private Wife wife;
	
	public Husband() {
		
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@OneToOne
	@PrimaryKeyJoinColumn
	public Wife getWife() {
		return wife;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	

}
