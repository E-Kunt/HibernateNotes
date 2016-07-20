package com.ekunt.model;

import java.util.Date;

/**
 * 主键映射 使用XML中的component
 * @author E-Kunt
 *
 */
public class Student {
	
	private int id;
	private String name;
	private Date birthday;
	private Address address;
	
	public Student() {
		
	}
	
	public Student(int id, String name, Date birthday, Address address) {
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
	
}
