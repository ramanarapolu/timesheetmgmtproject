package com.herguan.cs562.action;

import java.util.Date;

import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.dao.CommonDAO;
import com.herguan.cs562.model.Department;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class Preload extends ActionSupport {
	private void getData() {
		CommonDAO.getRole();
		CommonDAO.getUser();
	}

	private void preloadRole() {
		CommonDAO.deleteRole();
		Role role = new Role("Admin");
		role
				.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
						"Admin")));
		CommonDAO.createRole(role);

		Role role1 = new Role("Student");
		role1.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		CommonDAO.createRole(role1);

		Role role2 = new Role("Staff");
		role2
				.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
						"Staff")));
		CommonDAO.createRole(role2);

		Role role3 = new Role("Supervisor");
		role3.setKey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Supervisor")));
		CommonDAO.createRole(role3);
		CommonDAO.getRole();

	}

	private void preloadUser() {
		CommonDAO.deleteUser();
		User user = null;
		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Admin");
		user
				.setKey((KeyFactory.createKey(User.class.getSimpleName(),
						"Admin")));
		user.setLastName("Admin");
		user.setLogin("Admin");
		user.setPassword("Admin123");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Admin")));
		user.setRole("Admin");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		CommonDAO.createUser(user);

		user = new User();

		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Vidya");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		user.setRole("Student");
		user.setStudentID(102191);
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getStudentID())));
		user.setLastName("Venkataraman");
		user.setLogin("venkataraman.vidya");
		user.setPassword("Student123");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setProgramID("MSCS");
		user.setProgramName("Masters in Computer Science");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();

		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Anant");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		user.setRole("Student");
		user.setStudentID(912093);
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getStudentID())));
		user.setLastName("Pednekar");
		user.setLogin("pednekar.anant");
		user.setPassword("Anant123");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setProgramID("MSCS");
		user.setProgramName("Masters in Computer Science");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();

		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Anuradha");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		user.setRole("Student");
		user.setStudentID(102105);
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getStudentID())));
		user.setLastName("Shringarpure");
		user.setLogin("shringarpure.anuradha");
		user.setPassword("Anuradha123");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setProgramID("MSCS");
		user.setProgramName("Masters in Computer Science");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();

		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Jade");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		user.setRole("Student");
		user.setStudentID(102192);
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getStudentID())));
		user.setLastName("Smith");
		user.setLogin("smith.jade");
		user.setPassword("Jade123");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setProgramID("MSCS");
		user.setProgramName("Masters in Computer Science");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();

		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Jane");
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		user.setRole("Student");
		user.setStudentID(102193);
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getStudentID())));
		user.setLastName("Fonda");
		user.setLogin("fonda.jane");
		user.setPassword("Jane123");
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setProgramID("MSCS");
		user.setProgramName("Masters in Computer Science");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("John");
		user.setLastName("Smith");
		user.setLogin("smith.john");
		user.setPassword("john123");
		user.setEmployeeID(1);
		user.setRole("Supervisor");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getEmployeeID())));
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Supervisor")));
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setDeptKey(KeyFactory.createKey(Department.class.getSimpleName(),
				"AC"));
		user.setDeptName("Academics");
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		CommonDAO.createUser(user);

		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Mary");
		user.setLastName("Smith");
		user.setLogin("smith.mary");
		user.setPassword("mary123");
		user.setEmployeeID(2);
		user.setRole("Staff");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getEmployeeID())));
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff")));
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		user.setDeptKey(KeyFactory.createKey(Department.class.getSimpleName(),
				"HR"));
		user.setDeptName("Human Resources");
		CommonDAO.createUser(user);
		
		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Robert");
		user.setLastName("Zhu");
		user.setLogin("zhu.robert");
		user.setPassword("Robert123");
		user.setEmployeeID(3);
		user.setRole("Staff");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getEmployeeID())));
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff")));
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		user.setDeptKey(KeyFactory.createKey(Department.class.getSimpleName(),
				"AC"));
		user.setDeptName("Academics");
		CommonDAO.createUser(user);
		
		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Andy");
		user.setLastName("Zhu");
		user.setLogin("zhu.andy");
		user.setPassword("Andy123");
		user.setEmployeeID(4);
		user.setRole("Staff");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getEmployeeID())));
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff")));
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		user.setDeptKey(KeyFactory.createKey(Department.class.getSimpleName(),
				"AC"));
		user.setDeptName("Academics");
		CommonDAO.createUser(user);
		
		user = new User();
		user.setCreatedBy("Application Preload");
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		user.setFirstName("Jai");
		user.setLastName("Reddy");
		user.setLogin("reddy.jai");
		user.setPassword("Jai123");
		user.setEmployeeID(5);
		user.setRole("Staff");
		user.setKey((KeyFactory.createKey(User.class.getSimpleName(), user
				.getRole()
				+ user.getEmployeeID())));
		user.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff")));
		user.setUpdatedBy("Application Preload");
		user.setUpdatedDate(new Date(System.currentTimeMillis()));
		user.setKeyToString(KeyFactory.keyToString(user.getKey()));
		user.setDeptKey(KeyFactory.createKey(Department.class.getSimpleName(),
				"AC"));
		user.setDeptName("Academics");
		CommonDAO.createUser(user);

	}

	private void preloadData() {

		preloadRole();
		preloadUser();

	}

	public String preload() {
		try {
			preloadData();
			addActionMessage("Success in preloading");
			return SUCCESS;
		} catch (Exception ex) {
			addActionError("Error in preloading.");
			ex.printStackTrace();
		}

		return ERROR;
	}
}
