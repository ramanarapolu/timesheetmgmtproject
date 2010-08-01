package com.herguan.cs562.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Staff {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Integer employeeID;

	@Persistent
	private Long deptID;

	@Persistent
	private String deptName;

	public Integer getEmployeeID() {
		return employeeID;
	}

	public Long getDeptID() {
		return deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public void setDeptID(Long deptID) {
		this.deptID = deptID;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	
}
