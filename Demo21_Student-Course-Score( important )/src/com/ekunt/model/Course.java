package com.ekunt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	private int id;
	private String name;
	private Set<Student> students = new HashSet<Student>();

	@ManyToMany(mappedBy="courses")
	public Set<Student> getStudents() {
		return students;
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
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
}
