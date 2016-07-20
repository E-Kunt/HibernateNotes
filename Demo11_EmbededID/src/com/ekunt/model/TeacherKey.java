package com.ekunt.model;

import java.io.Serializable;

/**
 * 联合主键类，必须：
 * 1.实现Serializable
 * 2.重写equals
 * 3.重写hashCode
 * @author E-Kunt
 *
 */
public class TeacherKey implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	public TeacherKey(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TeacherKey() {
		super();
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TeacherKey) {
			TeacherKey o = (TeacherKey)obj;
			if(o.id == this.id && o.name.equals(this.name)){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode() + this.id;
	}
	
	@Override
	public String toString() {
		return "TeacherKey [id=" + id + ", name=" + name + "]";
	}
	
	
}
