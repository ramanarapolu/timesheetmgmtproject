package com.herguan.cs562.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Student {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Integer studentID;

	@Persistent
	private String programID;

	@Persistent
	private String programName;

	public Integer getStudentID() {
		return studentID;
	}

	public String getProgramID() {
		return programID;
	}

	public String getProgramName() {
		return programName;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	

}
