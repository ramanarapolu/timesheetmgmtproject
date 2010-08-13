package com.herguan.cs562.action;

import com.opensymphony.xwork2.ActionSupport;


public class Logout extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String logout() throws Exception {
		System.out.println("Hi");
		return SUCCESS;
	}
}
