package com.ekunt.entity;

import java.util.Date;

public class Emp {
	private int empno;
	private String ename;
	private Date hiredate;
	private int sal;
	private Dept dept;
	
	public Emp() {
	
	}

	public Emp(String ename, Date hiredate, int sal) {
		this.ename = ename;
		this.hiredate = hiredate;
		this.sal = sal;
	}

	
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", hiredate=" + hiredate + ", sal=" + sal + ", dept=" + dept
				+ "]";
	}
	
}
