package com.ekunt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;

/**
 * 联合主键的注解写法1：@EmbeddedId
 * @author E-Kunt
 *
 */
@Entity
public class Teacher {
	/**
	 * 主键为id+name的联合主键，封装成联合主键类
	 */
	private TeacherKey pk;
	private Date birthday;
	private int pay;
	private String address;
	
	public Teacher() {
		
	}

	
	public Teacher(TeacherKey pk, Date birthday, int pay, String address) {
		super();
		this.pk = pk;
		this.birthday = birthday;
		this.pay = pay;
		this.address = address;
	}

	@EmbeddedId
	public TeacherKey getPk() {
		return pk;
	}

	public void setPk(TeacherKey pk) {
		this.pk = pk;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Teacher [pk=" + pk + ", birthday=" + birthday + ", pay=" + pay + ", address=" + address + "]";
	}

	

	
	
}
