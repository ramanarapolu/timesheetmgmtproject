//persistent user object
package com.herguan.cs562.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User implements Serializable {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String login;

	@Persistent
	private String password;

	@Persistent
	private String firstName;

	@Persistent
	private String lastName;

	@Persistent
	private Key rolekey;

	@Persistent
	private String role;

	@Persistent
	private Date createdDate;

	@Persistent
	private String createdBy;

	@Persistent
	private Date updatedDate;

	@Persistent
	private String updatedBy;

    @Persistent    
	private Set<Key> allocationSet;
	

	private String keyToString;
	
	@Persistent
	private Integer employeeID;

	@Persistent
	private Key deptKey;

	@Persistent
	private String deptName;
	
	@Persistent
	private Integer studentID;

	@Persistent
	private String programID;

	@Persistent
	private String programName;

	public Key getRolekey() {
		return rolekey;
	}

	public void setRolekey(Key rolekey) {
		this.rolekey = rolekey;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Key getDeptKey() {
		return deptKey;
	}

	public void setDeptKey(Key deptKey) {
		this.deptKey = deptKey;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getProgramID() {
		return programID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getKeyToString() {
		return keyToString;
	}

	public void setKeyToString(String keyToString) {
		this.keyToString = keyToString;
		System.out.println("in key to string :" + keyToString);
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public Set<Key> getAllocationSet() {
		return allocationSet;
	}

	public void setAllocationSet(Set<Key> allocationSet) {
		this.allocationSet = allocationSet;
	}

}
