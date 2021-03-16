package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.sun.istack.internal.NotNull;


@Entity
@Table(name = "STUDS")
public class Student {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private int studId;

	@NotNull
	@Column(name = "STUD_NAME", nullable = false)
	private String studName;

	@Column(name = "STUD_AGE")
	private int studAge;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLASS_ID")
	private ClassRoom studCl;

	public ClassRoom getstudCl() {
		return studCl;
	}

	public void setstudCl(ClassRoom studCl) {
		this.studCl = studCl;
	}

	public int getStudId() {
		return studId;
	}

	public void setStudId(int studId) {
		this.studId = studId;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getStudAge() {
		return studAge;
	}

	public void setStudAge(int studAge) {
		this.studAge = studAge;
	}

	public Student(String studName, int studAge, ClassRoom studCl) {
		super();
		this.studName = studName;
		this.studAge = studAge;
		this.studCl=studCl;
	}
	
	public Student(String studName, int studAge) {
		super();
		this.studName = studName;
		this.studAge = studAge;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
