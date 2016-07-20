package com.ekunt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE) //允许二级缓存读写
public class Teacher {
	
	private int id;
	private String name;
	private Date birthday;
	private int pay;
	private String address;
	
	public Teacher() {
		
	}

	public Teacher(String name, Date birthday, int pay, String address) {
		this.name = name;
		this.birthday = birthday;
		this.pay = pay;
		this.address = address;
	}

	@Id
	@GeneratedValue
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
