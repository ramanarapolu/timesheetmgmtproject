//persistent user object
package com.herguan.cs562.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
    private Key studentKey;
	
	@Persistent
    private Key staffKey;
	
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
	
	private String role;

	@Persistent
	private Date createdDate;

	@Persistent
	private String createdBy;

	@Persistent
	private Date updatedDate;

	@Persistent
	private String updatedBy;

	

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

	public Key getRoleId() {
		return rolekey;
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

	public void setRoleKey(Key rolekey) {
		this.rolekey = rolekey;
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

	public Key getStudentKey() {
		return studentKey;
	}

	public void setStudentKey(Key studentKey) {
		this.studentKey = studentKey;
	}

	public Key getStaffKey() {
		return staffKey;
	}

	public void setStaffKey(Key staffKey) {
		this.staffKey = staffKey;
	}
	
	public void setRole(String role){
		this.role = role;
	}
	
	public String getRole(){
		return role;
	}


	
}
