package com.ekunt.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

/**
 * @author E-Kunt
 *
 */
@Entity
public class Grade implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private Set<Student> students;
	
	public Grade() {
		
	}

	public Grade(String name, String desc) {
		this.name = name;
		this.description = desc;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@ManyToMany
	@JoinTable(
		name="grade_std", //中间表的名字
		joinColumns={@JoinColumn(name="gid")}, //中间表关于本表外键的名字
		inverseJoinColumns={@JoinColumn(name="sid")} //中间表关于对方表外键的名字
	)
	public Set<Student> getStudents() {
		return students;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}


	@Override
	public String toString() {
		return "Grade [id=" + id + ", name=" + name + ", description=" + description +"]";
	}

	
	
	
}
