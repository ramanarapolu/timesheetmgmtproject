package com.herguan.cs562.model;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public class UserAllocationVO {
	
	private Key userKey;
	private String firstName;
	private String lastName;
	
	private List<TimesheetAllocationVO> tsVO;

	
	public Key getUserKey() {
		return userKey;
	}

	public void setUserKey(Key userKey) {
		this.userKey = userKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TimesheetAllocationVO> getTsVO() {
		return tsVO;
	}

	public void setTsVO(List<TimesheetAllocationVO> tsVO) {
		this.tsVO = tsVO;
	}
	
	

}
