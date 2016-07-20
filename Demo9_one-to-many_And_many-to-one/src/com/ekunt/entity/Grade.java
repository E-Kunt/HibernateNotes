package com.ekunt.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * @author E-Kunt
 *
 */
public class Grade implements Serializable {
	private int id;
	private String name;
	private String description;
	private Set<Student> students;
	
	//@OneToMany(mappedBy="grade",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	//集合是List时，可以用@OrderBy("age asc, id desc")
	//集合是Map时，可以用@MapKey(name="id")
	public Set<Student> getStudents() {
		return students;
	}
	
	public Grade() {
		
	}

	public Grade(String name, String desc) {
		this.name = name;
		this.description = desc;
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
