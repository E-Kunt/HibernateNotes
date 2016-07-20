package com.ekunt.model;

import java.util.Date;

public class Student {
	
	private int id;
	private String name;
	private Date birthday;
	private String address;
	
	public Student() {
		
	}
	
	public Student(int id, String name, Date birthday, String address) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
	}

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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
}
