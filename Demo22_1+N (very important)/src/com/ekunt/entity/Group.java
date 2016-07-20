package com.ekunt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;


@Entity
@Table(name="t_group")
//@BatchSize(size=2) //1+N问题解决方法2
public class Group implements Serializable {
	private int id;
	private String name;
	private String description;
	
	public Group() {
		
	}
	
	public Group(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
