package com.herguan.cs562.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.dao.AdminDAO;
import com.herguan.cs562.dao.CommonDAO;
import com.herguan.cs562.dao.UserDAO;
import com.herguan.cs562.model.Department;
import com.herguan.cs562.model.Project;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport {
	private static final long serialVersionUID = -2613425890762568273L;

	private String deptID;
	private String deptName;
	private String projectID;
	private String projectName;
	private String manager;
	private List<Department> departments;
	private List<Role> roles;
	private List<String> departmentNames;
	private List<User> staffUsers;
	private List<User> allUsers;
	private String team;
	private List<Project> projects;
	private User user;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String actionName;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<User> getStaffUsers() {
		return staffUsers;
	}

	public void setStaffUsers(List<User> staffUsers) {
		this.staffUsers = staffUsers;
	}

	public List<String> getDepartmentNames() {
		System.out.println("called : " + departmentNames);
		return departmentNames;
	}

	public void setDepartmentNames(List<String> departmentNames) {
		this.departmentNames = departmentNames;
	}

	public List<Department> getDepartments() {

		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	// @RequiredStringValidator(message="Department ID is mandatory")
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	// @RequiredStringValidator(message="Department Name is mandatory")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String createStudent() {
		System.out.println("user info :" + user.getFirstName());
		System.out.println("user info :" + user.getLastName());
		System.out.println("user info :" + user.getLogin());
		System.out.println("user info :" + user.getPassword());
		System.out.println("user info :" + user.getProgramID());
		System.out.println("user info :" + user.getProgramName());
		System.out.println("user info :" + user.getRolekey());
		System.out.println("user info :" + user.getStudentID());

		User userDB = new User();
		userDB.setFirstName(user.getFirstName());
		userDB.setLastName(user.getLastName());
		userDB.setLogin(user.getLogin());
		userDB.setPassword(user.getPassword());
		userDB.setProgramID(user.getProgramID());
		userDB.setProgramName(user.getProgramName());
		userDB.setStudentID(user.getStudentID());

		userDB.setCreatedBy("Application");
		userDB.setCreatedDate(new Date(System.currentTimeMillis()));
		userDB.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Student")));
		userDB.setRole("Student");
		userDB.setKey((KeyFactory.createKey(User.class.getSimpleName(), userDB
				.getRole()
				+ userDB.getStudentID())));
		userDB.setUpdatedBy("Application");
		userDB.setUpdatedDate(new Date(System.currentTimeMillis()));
		userDB.setKeyToString(KeyFactory.keyToString(userDB.getKey()));
		CommonDAO.createUser(userDB);

		addActionMessage("You have successfully created a student user.");
		return "User";
	}

	public String createStaff() {
		System.out.println("user info :" + user.getFirstName());
		System.out.println("user info :" + user.getLastName());
		System.out.println("user info :" + user.getLogin());
		System.out.println("user info :" + user.getPassword());
		System.out.println("user info :" + user.getEmployeeID());
		System.out.println("user info :" + deptID);

		User userDB = new User();
		userDB.setFirstName(user.getFirstName());
		userDB.setLastName(user.getLastName());
		userDB.setLogin(user.getLogin());
		userDB.setPassword(user.getPassword());
		userDB.setEmployeeID(user.getEmployeeID());
		userDB.setCreatedBy("Application");
		userDB.setCreatedDate(new Date(System.currentTimeMillis()));
		userDB.setRolekey((KeyFactory.createKey(Role.class.getSimpleName(),
				"Staff")));
		userDB.setRole("Staff");
		userDB.setKey((KeyFactory.createKey(User.class.getSimpleName(), userDB
				.getRole()
				+ userDB.getEmployeeID())));
		userDB.setUpdatedBy("Application");
		userDB.setUpdatedDate(new Date(System.currentTimeMillis()));
		userDB.setKeyToString(KeyFactory.keyToString(userDB.getKey()));
		userDB.setDeptKey(KeyFactory.createKey(
				Department.class.getSimpleName(), deptID));

		List<Department> departments = AdminDAO.getDepartments();
		for (Iterator<Department> it = departments.iterator(); it.hasNext();) {
			Department u = it.next();
			if (u.getDeptCode().equals(deptID)) {
				userDB.setDeptName(u.getDeptName());
			}
		}

		CommonDAO.createUser(userDB);

		addActionMessage("You have successfully created a staff user.");
		return "User";
	}

	public void showCreateUser() {
		setActionName("createUser");
		departments = AdminDAO.getDepartments();
		roles = AdminDAO.getRoles();
		// return "User";

	}

	private String html = "";

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

		public String showCreateUserTabbedPane() {
		showCreateUser();
		return "User";

	}

	public String showUserTabbedPane() {
		allUsers = UserDAO.getAllUsers();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("allUsers", allUsers);
		System.out.println("test"+allUsers.size());
		return "User";

	}
	
	public void viewUsers() {
		allUsers = UserDAO.getAllUsers();
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("allUsers", allUsers);
		System.out.println("test"+allUsers.size());
		

	}
	public String createProject() {

		System.out.println("action :" + actionName);
		departments = AdminDAO.getDepartments();
		if (actionName.equals("Create Project")) {

			if (!AdminDAO.getProjectExists(projectID)) {

				createProjectDAO();
				addActionMessage("You have successfully created a project.");
				return "Project";
			}

		}
		if (actionName.equals("Edit Project")) {
			editProjectDAO();
			addActionMessage("You have successfully edited a project.");
			return "Project";

		}
		addActionError("Project code exists in the database.");
		return INPUT;

	}

	public String showProject() {
		projects = AdminDAO.getProjects();
		List<User> dbUsers = UserDAO.getAllUsers();

		Project project = null;
		User u = null;
		Set<Key> userKeys = null;

		for (Iterator<Project> it1 = projects.iterator(); it1.hasNext();) {
			project = it1.next();
			userKeys = project.getUsersTeam();

			for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
				u = it.next();
				if (userKeys.contains(u.getKey())) {
					project.setUserTeam(u.getLastName() + " "
							+ u.getFirstName());
				}
				if (u.getKey().equals(project.getManagerKey())) {

					project.setManagerName(u.getLastName() + " "
							+ u.getFirstName());
				}

			}
		}
		System.out.println("projects :" + projects);
		return "Project";
	}

	public String showCreateProject() {
		setActionName("createProject");
		departments = AdminDAO.getDepartments();
		departmentNames = new ArrayList<String>();
		List<User> dbUsers = UserDAO.getAllUsers();
		staffUsers = new ArrayList<User>();
		allUsers = new ArrayList<User>();
		for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
			User u = it.next();
			if (u.getRole().equals("Staff") || u.getRole().equals("Supervisor")) {

				staffUsers.add(u);
			}
			if (!u.getRole().equals("Admin")) {
				allUsers.add(u);
			}
		}
		for (Department d : departments) {
			departmentNames.add(d.getDeptName());
		}
		return "Project";

	}

	public String showDepartment() {
		departments = AdminDAO.getDepartments();
		return "Department";
	}

	public String createDepartment() {
		System.out.println("action :" + actionName);
		if (actionName.equals("Create Department")) {
			Department dept = getDepartment();
			if (dept == null) {
				createDepartmentDAO();
				addActionMessage("You have successfully created a department.");
				return "Department";
			}
		}
		if (actionName.equals("Edit Department")) {
			editDepartmentDAO();
			addActionMessage("You have successfully edited a department.");
			return "Department";

		}
		addActionError("Department exists in the database.");
		return INPUT;

	}

	public String editProject() {
		setActionName("editProject");
		System.out.println("params :" + projectID + " : " + projectName + ":"
				+ deptName);
		departments = AdminDAO.getDepartments();
		departmentNames = new ArrayList<String>();
		List<User> dbUsers = UserDAO.getAllUsers();
		staffUsers = new ArrayList<User>();
		allUsers = new ArrayList<User>();
		for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
			User u = it.next();
			if (u.getRole().equals("Staff") || u.getRole().equals("Supervisor")) {

				staffUsers.add(u);
			}
			if (!u.getRole().equals("Admin")) {
				allUsers.add(u);
			}
		}
		for (Department d : departments) {
			departmentNames.add(d.getDeptName());
		}
		return "Project";

	}

	public String editDepartment() {
		setActionName("editDepartment");
		return "Department";

	}

	public String showCreateDepartment() {

		setActionName("createDepartment");
		return "Department";

	}

	private Department getDepartment() {

		Department dept = null;
		try {
			dept = AdminDAO.getDepartment(deptID);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return dept;
	}

	private void createDepartmentDAO() {

		try {

			Department dept = new Department();
			dept.setDeptCode(deptID);
			dept.setDeptName(deptName);
			dept.setKey(KeyFactory.createKey(Department.class.getSimpleName(),
					deptID));
			AdminDAO.createDepartment(dept);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	private void editDepartmentDAO() {

		try {

			Department dept = new Department();
			dept.setDeptCode(deptID);
			dept.setDeptName(deptName);
			dept.setKey(KeyFactory.createKey(Department.class.getSimpleName(),
					deptID));
			AdminDAO.editDepartment(dept);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	private void createProjectDAO() {

		try {

			List<User> dbUsers = UserDAO.getAllUsers();
			Map<String, User> userMap = new HashMap<String, User>();

			for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
				User u = it.next();
				if (!u.getRole().equals("Admin")) {
					userMap.put(u.getKey().toString(), u);
				}
			}
			System.out.println("user map :" + userMap);

			Project project = new Project();
			project.setProjectCode(projectID);
			project.setProjectName(projectName);
			project.setDeptKey(KeyFactory.createKey(Department.class
					.getSimpleName(), deptID));
			project.setManagerKey(KeyFactory.stringToKey(manager));

			System.out.println("printing proj values " + team);

			Set<Key> userKeys = new HashSet<Key>();
			StringTokenizer str = new StringTokenizer(team, ",");
			String temp = null;
			while (str.hasMoreElements()) {
				temp = str.nextElement().toString();
				System.out.println("in map : " + temp.trim());
				System.out.println("in map : " + userMap.get(temp.trim()));
				// User t = (User)userMap.get(temp.trim());
				// Key k = ((User)userMap.get(temp.trim())).getKey();
				userKeys.add(((User) userMap.get(temp.trim())).getKey());
			}
			project.setUsersTeam(userKeys);

			System.out.println("key :" + project.getManagerKey());
			for (Iterator<Department> it = departments.iterator(); it.hasNext();) {
				Department u = it.next();
				if (u.getDeptCode().equals(deptID)) {
					project.setDeptName(u.getDeptName());
				}
			}
			project.setKey(KeyFactory.createKey(Project.class.getSimpleName(),
					project.getProjectCode()));
			AdminDAO.createProject(project);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

	private void editProjectDAO() {

		try {

			List<User> dbUsers = UserDAO.getAllUsers();
			Map<String, User> userMap = new HashMap<String, User>();

			for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
				User u = it.next();
				if (!u.getRole().equals("Admin")) {
					userMap.put(u.getKey().toString(), u);
				}
			}

			Project project = new Project();
			project.setProjectCode(projectID);
			project.setProjectName(projectName);
			project.setDeptKey(KeyFactory.createKey(Department.class
					.getSimpleName(), deptID));
			project.setManagerKey(KeyFactory.stringToKey(manager));

			Set<Key> userKeys = new HashSet<Key>();
			StringTokenizer str = new StringTokenizer(team, ",");
			String temp = null;
			while (str.hasMoreElements()) {
				temp = str.nextElement().toString();
				userKeys.add(((User) userMap.get(temp.trim())).getKey());
			}
			project.setUsersTeam(userKeys);

			for (Iterator<Department> it = departments.iterator(); it.hasNext();) {
				Department u = it.next();
				if (u.getDeptCode().equals(deptID)) {
					project.setDeptName(u.getDeptName());
				}
			}
			project.setKey(KeyFactory.createKey(Project.class.getSimpleName(),
					project.getProjectCode()));
			AdminDAO.editProject(project);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}

}
