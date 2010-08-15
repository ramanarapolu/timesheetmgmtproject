package com.herguan.cs562.model;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

public class TimesheetHistory implements Serializable{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Timesheet timesheet;
	
	@Persistent
	private TimesheetStatusEnum timesheet_status;
	
	//concatenation of year and week number
	@Persistent
	private String weekId;
	
	@Persistent
	private Integer sundayHours;
	@Persistent
	private Integer mondayHours;
	@Persistent
	private Integer tuesdayHours;
	@Persistent
	private Integer wednesdayHours;
	@Persistent
	private Integer thursdayHours;
	@Persistent
	private Integer fridayHours;
	@Persistent
	private Integer saturdayHours;
	@Persistent
	private Integer totalWeekHours;
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Timesheet getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}
	public TimesheetStatusEnum getTimesheet_status() {
		return timesheet_status;
	}
	public void setTimesheet_status(TimesheetStatusEnum timesheetStatus) {
		timesheet_status = timesheetStatus;
	}
	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}
	public Integer getSundayHours() {
		return sundayHours;
	}
	public void setSundayHours(Integer sundayHours) {
		this.sundayHours = sundayHours;
	}
	public Integer getMondayHours() {
		return mondayHours;
	}
	public void setMondayHours(Integer mondayHours) {
		this.mondayHours = mondayHours;
	}
	public Integer getTuesdayHours() {
		return tuesdayHours;
	}
	public void setTuesdayHours(Integer tuesdayHours) {
		this.tuesdayHours = tuesdayHours;
	}
	public Integer getWednesdayHours() {
		return wednesdayHours;
	}
	public void setWednesdayHours(Integer wednesdayHours) {
		this.wednesdayHours = wednesdayHours;
	}
	public Integer getThursdayHours() {
		return thursdayHours;
	}
	public void setThursdayHours(Integer thursdayHours) {
		this.thursdayHours = thursdayHours;
	}
	public Integer getFridayHours() {
		return fridayHours;
	}
	public void setFridayHours(Integer fridayHours) {
		this.fridayHours = fridayHours;
	}
	public Integer getSaturdayHours() {
		return saturdayHours;
	}
	public void setSaturdayHours(Integer saturdayHours) {
		this.saturdayHours = saturdayHours;
	}
	public Integer getTotalWeekHours() {
		return totalWeekHours;
	}
	public void setTotalWeekHours(Integer totalWeekHours) {
		this.totalWeekHours = totalWeekHours;
	}
	
	
	
}
