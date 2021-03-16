package com.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHERS")
public class Teacher {
	
	@Id
	@Column(name = "TID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int teachId;
	
	@Column(name = "TNAME", nullable = false)
	private String teachName;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name= "TEACHER_SUBS", joinColumns = { @JoinColumn(name ="TEACHER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "SUB_CLASS_ID") })
	private List<SubjectClass> scPair;
	

	public List<SubjectClass> getScPair() {
		return scPair;
	}

	public void setScPair(List<SubjectClass> scPair) {
		this.scPair = scPair;
	}

	public Teacher(String teachName) {
		super();
		this.teachName = teachName;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTeachId() {
		return teachId;
	}

	public void setTeachId(int teachId) {
		this.teachId = teachId;
	}

	public String getTeachName() {
		return teachName;
	}

	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}

	@Override
	public String toString() {
		return "Teacher [teachId=" + teachId + ", teachName=" + teachName + ", scPair=" + scPair + "]";
	}

	

	
	
}
