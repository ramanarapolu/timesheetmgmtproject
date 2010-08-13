package com.herguan.cs562.model;

import java.util.List;
import java.util.Map;

public class ViewTimesheetScreenVO {

	private Integer employeeID;

	private Integer studentID;

	private String firstName;

	private String lastName;

	private String supervisorName;

	private String departmentName;

	private List<String> weeks;

	private Map<String, TimesheetAllocationVO> weekTimesheetAllocationMap;

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
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

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<String> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<String> weeks) {
		this.weeks = weeks;
	}

	public Map<String, TimesheetAllocationVO> getWeekTimesheetAllocationMap() {
		return weekTimesheetAllocationMap;
	}

	public void setWeekTimesheetAllocationMap(
			Map<String, TimesheetAllocationVO> weekTimesheetAllocationMap) {
		this.weekTimesheetAllocationMap = weekTimesheetAllocationMap;
	}
	
	
	

	
}
