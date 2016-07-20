package com.ekunt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

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
	@Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="wifeId")
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
