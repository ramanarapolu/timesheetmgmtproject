package com.herguan.cs562.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.dao.AdminDAO;
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
	private List<String> departmentNames;
	private List<User> staffUsers;
	private List<User> allUsers;
	private String team;
	private List<Project> projects;

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

	public String createUser() {
		return "User";
	}

	public String createProject() {

		departments = AdminDAO.getDepartments();
		if (!AdminDAO.getProjectExists(projectID)) {
			
			createProjectDAO();
			addActionMessage("You have successfully created a project.");
			return "Project";
		}
		addActionError("Project code exists in the database.");
		return INPUT;

	}

	public String showProject() {
		projects = AdminDAO.getProjects();
		System.out.println("projects :"+projects);
		return "Project";
	}

	public String showCreateProject() {

		departments = AdminDAO.getDepartments();
		departmentNames = new ArrayList<String>();
		List<User> dbUsers = UserDAO.getAllUsers();
		staffUsers = new ArrayList<User>();
		allUsers = new ArrayList<User>();
		System.out.println("print results " + staffUsers);

		/*
		 * if (staffUsers.iterator().hasNext()) { for (User u : staffUsers) { if
		 * (!u.getRoleId().equals(staffKey)) { System.out.println("not staff :"
		 * + u); staffUsers.iterator().remove(); } } }
		 */
		for (Iterator<User> it = dbUsers.iterator(); it.hasNext();) {
			User u = it.next();
			System.out.println("key :" + u.getKey());
			System.out.println("key id :" + u.getKey().getId());

			System.out.println("key to string :"
					+ KeyFactory.keyToString(u.getKey()));
			System.out.println("string to key :"
					+ KeyFactory
							.stringToKey(KeyFactory.keyToString(u.getKey())));

			System.out.println("roles :" + u.getRolekey());
			if (u.getRole().equals("Staff") ||
					u.getRole().equals("Supervisor")) {
				
				System.out.println("staff :" + u.getRolekey());
				staffUsers.add(u);
			}
			if (!u.getRole().equals("Admin")) {
				System.out.println("admin :" + u.getRolekey());
				allUsers.add(u);
			}
		}
		System.out.println("print results " + staffUsers);
		System.out.println("print results " + allUsers);
		for (Department d : departments) {
			departmentNames.add(d.getDeptName());
		}
		System.out.println("depts :" + departmentNames);
		System.out.println(" dept s :" + departments);
		return "Project";

	}

	public String showDepartment() {
		departments = AdminDAO.getDepartments();
		return "Department";
	}

	public String createDepartment() {
		Department dept = getDepartment();
		if (dept == null) {
			createDepartmentDAO();
			addActionMessage("You have successfully created a department.");
			return "Department";
		}
		addActionError("Department exists in the database.");
		return INPUT;

	}

	public String showCreateDepartment() {

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
			System.out.println("user map :"+userMap);

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
				System.out.println("in map : "+temp.trim());
				System.out.println("in map : "+userMap.get(temp.trim()));
				//User t = (User)userMap.get(temp.trim());
				//Key k = ((User)userMap.get(temp.trim())).getKey();
				userKeys.add(((User)userMap.get(temp.trim())).getKey());
			}
			project.setUsersTeam(userKeys);

			System.out.println("key :" + project.getManagerKey());
			for (Iterator<Department> it = departments.iterator(); it.hasNext();) {
				Department u = it.next();
				if (u.getDeptCode().equals(deptID)) {
					project.setDeptName(u.getDeptName());
				}
			}
			project.setKey(KeyFactory.createKey(Project.class
					.getSimpleName(), project.getProjectCode()));
			 AdminDAO.createProject(project);
		} catch (Exception ex) {

			ex.printStackTrace();
		}

	}
}
