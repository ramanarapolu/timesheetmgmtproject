package com.herguan.cs562.model;

import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class Project {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String projectCode;

	@Persistent
	private String projectName;
	
	@Persistent
	private String deptName;
	
	@Persistent
	private Key deptKey;
	
	@Persistent
	private Key managerKey;
	
	@Persistent
    private Set<Key> usersTeam;


	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Key getDeptKey() {
		return deptKey;
	}

	public void setDeptKey(Key deptKey) {
		this.deptKey = deptKey;
	}

	public Key getManagerKey() {
		return managerKey;
	}

	public void setManagerKey(Key managerKey) {
		this.managerKey = managerKey;
	}

	public Set<Key> getUsersTeam() {
		return usersTeam;
	}

	public void setUsersTeam(Set<Key> usersTeam) {
		this.usersTeam = usersTeam;
	}
	
	
}
