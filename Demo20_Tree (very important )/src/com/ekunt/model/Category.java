package com.ekunt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {
	private int id;
	private String name;
	private Set<Category> children = new HashSet<Category>();
	private Category parent;
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	@OneToMany(mappedBy="parent",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Category> getChildren() {
		return children;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Category getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setChildren(Set<Category> children) {
		this.children = children;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
}
