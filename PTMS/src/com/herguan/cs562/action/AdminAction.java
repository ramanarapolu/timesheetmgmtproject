package com.herguan.cs562.action;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction  extends ActionSupport {
	private static final long serialVersionUID = -2613425890762568273L;

	public String createUser()
	{
		return "User";		
	}
	
	public String createProject()
	{
		return "Project";		
	}
	
	public String createDepartment()
	{
		return "Department";		
	}
	
	

}
