package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ClassRoom")
public class ClassRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int classId;

	@Column(name = "CLASS_NAME", unique = true)
	private String className;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studCl")
	private List<Student> studList = new ArrayList<Student>();

	
	@ManyToMany(mappedBy = "classList")
	private List<Subjects> subjectList = new ArrayList<Subjects>();

	
	public List<Subjects> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subjects> subjectList) {
		this.subjectList = subjectList;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStudList() {
		return studList;
	}

	public void setStudList(List<Student> studList) {
		this.studList = studList;
	}

	public ClassRoom(String className, List<Student> studList) {
		super();
		this.className = className;
		this.studList = studList;
	}


	public ClassRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassRoom(String className) {
		super();
		this.className = className;
	}



}
