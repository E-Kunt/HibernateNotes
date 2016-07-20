package com.ekunt.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 主键映射：使用注解@Embeded
 * @author E-Kunt
 *
 */
@Entity
public class Student2 {
	
	private int id;
	private String name;
	private Date birthday;
	private Address address;
	
	public Student2() {
		
	}
	
	public Student2(int id, String name, Date birthday, Address address) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
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
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
	
}
