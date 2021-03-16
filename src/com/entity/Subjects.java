package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECTS")
public class Subjects {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int subId;
	
	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	@Column(name="SUB_NAME", nullable=false)
	private String subName;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SUBJECTS_CLASS", joinColumns = { @JoinColumn(name = "SUB_IDS") }, inverseJoinColumns = {
			@JoinColumn(name = "CLASS_ID") })
	private List<ClassRoom> classList = new ArrayList<ClassRoom>(); 
	
	public List<ClassRoom> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassRoom> classList) {
		this.classList = classList;
	}

	public String getSubName() {
		return subName;
	}
	
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	
	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subjects(String subName) {
		super();
		this.subName = subName;
	}


	public Subjects(String subName, List<ClassRoom> clsforSubs) {
		super();
		this.subName = subName;
		this.classList=clsforSubs;
	}
	
	 
}
