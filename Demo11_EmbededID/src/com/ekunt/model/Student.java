package com.ekunt.model;

import java.util.Date;

public class Student {
	/**
	 * 主键为id+name的联合主键，封装成联合主键类
	 */
	private StudentKey pk;
	private Date birthday;
	private String address;
	
	public Student() {
		
	}

	public Student(StudentKey pk, Date birthday, String address) {
		super();
		this.pk = pk;
		this.birthday = birthday;
		this.address = address;
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

	public StudentKey getPk() {
		return pk;
	}


	public void setPk(StudentKey pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "Student [pk=" + pk + ", birthday=" + birthday + ", address=" + address + "]";
	}
	
}
