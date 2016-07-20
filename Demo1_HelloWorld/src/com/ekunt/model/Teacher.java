package com.ekunt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Teacher {
	
	private int id;
	private String name;
	private Date birthday;
	private int pay;
	private String address;
	
	public Teacher() {
		
	}

	public Teacher(int id, String name, Date birthday, int pay, String address) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.pay = pay;
		this.address = address;
	}

	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", birthday=" + birthday + ", pay=" + pay + ", address="
				+ address + "]";
	}
	
	
}
