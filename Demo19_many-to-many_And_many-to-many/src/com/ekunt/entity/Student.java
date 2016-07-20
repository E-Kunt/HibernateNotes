package com.ekunt.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student implements Serializable {
	private int id;
	private String name;
	private int age;
	private Set<Grade> grades;

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
	
	@ManyToMany(mappedBy="students") //多对多关系，已由对方类的students属性配置了
	public Set<Grade> getGrades() {
		return grades;
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



	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	

}
