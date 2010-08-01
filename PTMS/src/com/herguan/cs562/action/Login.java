package com.herguan.cs562.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.dao.CommonDAO;
import com.herguan.cs562.dao.UserDAO;
import com.herguan.cs562.model.Food;
import com.herguan.cs562.model.Person;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.Staff;
import com.herguan.cs562.model.Student;
import com.herguan.cs562.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private Map session;
	private String username;
	private String password;

	public String show() {
		return "show";
	}

	public void getData() {
		CommonDAO.getRole();
		CommonDAO.getUser();
		CommonDAO.getStudent();
		CommonDAO.getStaff();
	}

	public void preloadData() {

		Role role = new Role("Admin");
		role.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
						"Admin")));
		CommonDAO.createRole(role);

		Role role1 = new Role("Student");
		role1.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		CommonDAO.createRole(role1);

		Role role2 = new Role("Staff");
		role2.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
						"Staff")));
		CommonDAO.createRole(role2);

		Role role3 = new Role("Supervisor");
		role3.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Supervisor")));
		CommonDAO.createRole(role3);
		CommonDAO.getRole();
		
		User user = null;
		Student student = null;
		Staff staff = null;

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Admin first name");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(),
						"Admin")));
		user.setLastName("Admin last name");
		user.setLogin("Admin");
		user.setPassword("Admin123");
		user.setRoleKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Admin"))); // user.setStaffKey(staffKey); //
		//user.setStudentKey(studentKey);
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		CommonDAO.createUser(user);

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Student first name");
		user.setKey((KeyFactory
				.createKey(User.class.getSimpleName(), "Student")));
		user.setLastName("Student last name");
		user.setLogin("Student");
		user.setPassword("Student123");
		user.setRoleKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student"))); // user.setStaffKey(staffKey); //
		//user.setStudentKey(studentKey);
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		CommonDAO.createUser(user);

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Supervisor first name");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(),
				"Supervisor")));
		user.setLastName("Supervisor last name");
		user.setLogin("Supervisor");
		user.setPassword("Supervisor123");
		user.setRoleKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Supervisor"))); // user.setStaffKey(staffKey); //
		//user.setStudentKey(studentKey);
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		CommonDAO.createUser(user);

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Staff first name");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(),
						"Staff")));
		user.setLastName("Staff last name");
		user.setLogin("Staff");
		user.setPassword("Staff123");
		user.setRoleKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff"))); // user.setStaffKey(staffKey); //
		//user.setStudentKey(studentKey);
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		CommonDAO.createUser(user);

		student = new Student();
		student.setProgramID("MSCS");
		student.setProgramName("Masters in Computer Science");
		student.setStudentID(new Integer("102191"));
		student.setKey((KeyFactory.createKey(Student.class.getSimpleName(),
				"102191")));
		CommonDAO.createStudent(student);
		CommonDAO.updateUser(student);

		staff = new Staff();
		staff.setDeptID(new Long("1"));
		staff.setDeptName("Academics");
		staff.setEmployeeID(new Integer("1"));
		staff.setKey((KeyFactory.createKey(Staff.class.getSimpleName(), "1")));
		CommonDAO.createStaff(staff);
		CommonDAO.updateUser(staff);

	}

	public void createFoodPersons() {

		Food f = new Food();
		f.setFoodName("Pizza");
		Key fkey = (KeyFactory.createKey(Food.class.getSimpleName(), "Pizza"));
		f.setKey(fkey);

		Person p = new Person();
		Key pkey = (KeyFactory.createKey(Person.class.getSimpleName(), "John"));
		p.setKey(pkey);
		p.setName("John");
		p.setFavoriteFood(fkey);

		try {
			CommonDAO.createPerson(p);
			CommonDAO.createFood(f);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String login() {
		User user = validateUserAuthentication();

		if (user != null) {

			session.put("UserName", user.getFirstName());
			session.put("User", user);
			System.out.println(session.get("UserName"));
			addActionMessage("You are successfully logged in.");
			return SUCCESS;
		}


		addActionError("Username and Password Combination doesnot match.");
		return INPUT;
	}

	// login for preload
	/*
	public String login() {
		preloadData();

		if (username.equals("whyjava") && password.equals("password")) {
			addActionMessage("You are successfully logged in.");
			return SUCCESS;
		}

		addActionError("Username and Password Combination doesnot match.");
		return INPUT;
	}
*/
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

	@Override
	public void setSession(Map session) {
		this.session = session;

	}

}
