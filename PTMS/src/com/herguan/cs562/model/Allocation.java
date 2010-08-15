package com.herguan.cs562.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Allocation implements Serializable{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	

	
	@Persistent
	private Key projectKey;

	@Persistent
	private String projectName;
	
	@Persistent
	private Date startDate;
	
	@Persistent
	private Date endDate;
	
	@Persistent
	private Boolean isActive;
	
	@Persistent
	private Set<Key> timesheetKeys;
	

	public Set<Key> getTimesheetKeys() {
		return timesheetKeys;
	}

	public void setTimesheetKeys(Set<Key> timesheetKeys) {
		this.timesheetKeys = timesheetKeys;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	
	public Key getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(Key projectKey) {
		this.projectKey = projectKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
