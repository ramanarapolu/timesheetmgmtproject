package com.herguan.cs562.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	private static Map session;
	

	public Map getSession() {
		return session;
	}


	@Override
	public void setSession(Map session) {
		this.session = session;

	}
	

	public String logout() throws Exception {
		System.out.println("Hi");
		return SUCCESS;
	}
}
