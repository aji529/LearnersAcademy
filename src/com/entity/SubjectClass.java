package com.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECTS_CLASS")
public class SubjectClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assId;
	
	@Column(name = "SUB_IDS")
	private int subId;
	
	@Column(name = "CLASS_ID")
	private int classID;
	
	
	public int getAssId() {
		return assId;
	}
	public void setAssId(int assId) {
		this.assId = assId;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public SubjectClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubjectClass(int subId, int classID) {
		super();
		this.subId = subId;
		this.classID = classID;
	}
	
	
	

}
