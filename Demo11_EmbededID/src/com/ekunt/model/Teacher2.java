package com.ekunt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 联合主键的注解写法2 @IdClass + @Id
 * @author E-Kunt
 *
 */
@Entity
@IdClass(TeacherKey.class)
public class Teacher2 {
	private int id;
	private String name;
	private String address;
	private Date birthday;
	private int pay;
	
	public Teacher2() {
		
	}
	
	public Teacher2(int id, String name,String address,int pay,Date birthday) {
		this.address = address;
		this.birthday = birthday;
		this.id = id;
		this.name = name;
		this.pay = pay;
	}

	@Id
	public int getId() {
		return id;
	}
	
	@Id
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}

	public Date getBirthday() {
		return birthday;
	}



	public int getPay() {
		return pay;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	
	

	
	
}
