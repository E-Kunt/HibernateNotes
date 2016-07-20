package com.ekunt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score {

	private int id;
	private int point;
	private Student student;
	private Course course;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public int getPoint() {
		return point;
	}
	@ManyToOne
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	@ManyToOne
	@JoinColumn(name="course_id")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
