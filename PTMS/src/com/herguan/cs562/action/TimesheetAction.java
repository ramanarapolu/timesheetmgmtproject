package com.herguan.cs562.action;

import com.opensymphony.xwork2.ActionSupport;

public class TimesheetAction  extends ActionSupport{
	private static final long serialVersionUID = -2613425890762568273L;

	public String view()
	{
		return "view";		
	}
	
	public String submit()
	{
		return "submit";		
	}
	
	public String approve()
	{
		return "approve";		
	}
}
