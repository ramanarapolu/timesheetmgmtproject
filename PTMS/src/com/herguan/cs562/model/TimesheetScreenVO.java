package com.herguan.cs562.model;

import java.util.List;
import java.util.Map;


public class TimesheetScreenVO {

	
	private Integer employeeID;
	
	private Integer studentID;
	
	private String firstName;
	
	private String lastName;
	
	private String supervisorName;
	
	private String departmentName;
	
	private List<String> weeks;
	
	private List<Timesheet> timesheets;
	
	private Map<String, TimesheetAllocationVO> weekTimesheetMap;
	
	private Project project;
	
	private Allocation allocation;

	public Project getProject() {
		return project;
	}

	public Allocation getAllocation() {
		return allocation;
	}
	public TimesheetScreenVO(Integer employeeID, Integer studentID,Project project,
			Allocation allocation,
			String firstName, String lastName, String supervisorName,
			String departmentName, List<String> weeks,
			List<Timesheet> timesheets, Map<String, TimesheetAllocationVO> weekTimesheetMap) {
		super();
		this.employeeID = employeeID;
		this.studentID = studentID;
		this.firstName = firstName;
		this.allocation = allocation;
		this.lastName = lastName;
		this.supervisorName = supervisorName;
		this.departmentName = departmentName;
		this.weeks = weeks;
		this.project = project;
		this.timesheets = timesheets;
		this.weekTimesheetMap = weekTimesheetMap;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public List<String> getWeeks() {
		return weeks;
	}

	public List<Timesheet> getTimesheets() {
		return timesheets;
	}

	public Map<String, TimesheetAllocationVO> getWeekTimesheetMap() {
		return weekTimesheetMap;
	}
	
	
}
