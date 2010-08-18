package com.herguan.cs562.model;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Key;

public class ApproveTimesheetScreenVO {

	private Key projectKey;
	private String projectName;
	private String projectCode;
	private String department;
	private String team;

	Map<Key, List<UserAllocationVO>> projectUserMap;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		if (this.team != null && !this.team.trim().equals("")) {
			this.team = this.team + "," + team;
			return;
		}
		this.team = team;
	}

	public Map<Key, List<UserAllocationVO>> getProjectUserMap() {
		return projectUserMap;
	}

	public void setProjectUserMap(
			Map<Key, List<UserAllocationVO>> projectUserMap) {
		this.projectUserMap = projectUserMap;
	}

}
