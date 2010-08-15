package com.herguan.cs562.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.herguan.cs562.dao.UserDAO;
import com.herguan.cs562.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	public String show() {
		return "show";
	}

	public String login() {
		
		User user = validateUserAuthentication();

		if (user != null) {

			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("User", user);
			session.setAttribute("UserName", user.getFirstName());
			addActionMessage("You are successfully logged in.");

			return SUCCESS;
		}

		addActionError("Username and Password Combination doesnot match.");
		return INPUT;
	}

	private User validateUserAuthentication() {

		User user = null;

		try {
			// PasswordService ps = PasswordService.getInstance();
			user = new UserDAO().getUser(username, password);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
