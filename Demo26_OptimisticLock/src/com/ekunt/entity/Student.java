package com.ekunt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_student")
public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	private int version;

	public Student() {

	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * 乐观锁。
	 * 加了版本号，可以判断数据是否被改动
	 * @return
	 */
	@Version
	public int getVersion() {
		return version;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age +  "]";
	}



	public void setVersion(int version) {
		this.version = version;
	}
	

}
