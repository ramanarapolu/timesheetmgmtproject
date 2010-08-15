package com.herguan.cs562.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Allocation;
import com.herguan.cs562.model.Department;
import com.herguan.cs562.model.Project;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;

public class AdminDAO {
	
	@SuppressWarnings("unchecked")
	public static List<Role> getRoles() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Role.class);
		List<Role> results;
		List<Role> returnList;
		try {
			results = (List<Role>) query.execute();
			returnList = results;
			System.out.println("Success list" + returnList);
		} finally {
			pm.close();
		}
		return results;
	}
	public static void editDepartment(Department dept) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Department deptDB = pm.getObjectById(Department.class, dept
					.getKey());
			deptDB.setDeptName(dept.getDeptName());
			pm.makePersistent(deptDB);
			System.out.println("Success update");
		} finally {
			pm.close();
		}
	}

	public static void createDepartment(Department dept) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(dept);
			System.out.println("Success creation");
		} finally {
			pm.close();
		}
	}

	public static Boolean getProjectExists(String projectCode) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query query = pm
					.newQuery(Project.class, "projectCode == paramCode");
			query.declareParameters("java.lang.String paramCode");

			List<Project> results = (List<Project>) query.execute(projectCode);

			if (results.size() > 0) {
				return Boolean.TRUE;
			}

			System.out.println("Project exists");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static void createProject(Project project) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(project);
			Key userKey = null;
			Key allocationKey = null;
			User u = null;

			Allocation allocation = null;
			Allocation allocationInUser = null;
			Set<Key> allocationSet = null;

			updateManagersInUsers();
			for (Iterator<Key> it = project.getUsersTeam().iterator(); it
					.hasNext();) {
				userKey = it.next();
				u = pm.getObjectById(User.class, userKey);
				allocation = new Allocation();
				allocation.setKey(KeyFactory.createKey(Allocation.class
						.getSimpleName(), u.getLogin()
						+ project.getProjectCode()));
				allocation.setProjectKey(project.getKey());
				allocation.setProjectName(project.getProjectName());
				// allocation.setUserKey(u.getKey());
				// allocation.setUser(u);
				allocation.setIsActive(Boolean.TRUE);
				if (u.getAllocationSet() != null) {
					for (Iterator<Key> it1 = u.getAllocationSet().iterator(); it1
							.hasNext();) {
						allocationKey = it1.next();
						allocationInUser = pm.getObjectById(Allocation.class,
								allocationKey);
						allocationInUser.setIsActive(Boolean.FALSE);
					}
					u.getAllocationSet().add(allocation.getKey());
				} else {
					allocationSet = new HashSet<Key>();
					allocationSet.add(allocation.getKey());
					u.setAllocationSet(allocationSet);
				}
				pm.makePersistent(allocation);
				pm.makePersistent(u);
			}

		} finally {
			pm.close();
		}
	}

	public static void editProject(Project project) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Project projectDB = pm.getObjectById(Project.class, project
					.getKey());
			projectDB.setDeptKey(project.getDeptKey());
			projectDB.setDeptName(project.getDeptName());
			projectDB.setManagerKey(project.getManagerKey());
			projectDB.setProjectCode(project.getProjectCode());
			projectDB.setProjectName(project.getProjectName());
			Set<Key> tosetInProject = new HashSet<Key>();
			tosetInProject.addAll(project.getUsersTeam());
			

			// comparing allocations
			Set<Key> userTeam = project.getUsersTeam();
			Set<Key> userTeamDB =projectDB.getUsersTeam();
			
			Key userKey = null;
			for (Iterator<Key> it2 = userTeam.iterator(); it2.hasNext();) {
				userKey = it2.next();
				System.out.println("userteam key"+userKey);
				System.out.println("userteam key"+userTeamDB.contains(userKey));
				
				if (userTeamDB.contains(userKey)) {
					userTeamDB.remove(userKey);
					userTeam.remove(userKey);

				}
			}
			
			//projectDB.setUsersTeam(project.getUsersTeam());
			System.out.println(" user team db " + userTeamDB);
			System.out.println(" user team  " + userTeam);
			projectDB.setUsersTeam(tosetInProject);
			pm.makePersistent(projectDB);
			System.out.println("Success editing update");

			Key allocationKey = null;
			User u = null;

			Allocation allocation = null;
			Allocation allocationDB = null;
			Allocation allocationInUser = null;
			Set<Key> allocationSet = null;

			updateManagersInUsers();

			for (Iterator<Key> it = userTeamDB.iterator(); it.hasNext();) {
				userKey = it.next();
				u = pm.getObjectById(User.class, userKey);
				if (u.getAllocationSet() != null
						&& u.getAllocationSet().contains(
								KeyFactory.createKey(Allocation.class
										.getSimpleName(), u.getLogin()
										+ project.getProjectCode()))) {
					allocationDB = pm.getObjectById(Allocation.class,
							KeyFactory.createKey(Allocation.class
									.getSimpleName(), u.getLogin()
									+ project.getProjectCode()));
					allocationDB.setIsActive(Boolean.FALSE);
					pm.makePersistent(allocationDB);
					/*u.getAllocationSet().remove(
							KeyFactory.createKey(Allocation.class
									.getSimpleName(), u.getLogin()
									+ project.getProjectCode()));
					pm.makePersistent(u);*/
				}

			}

			for (Iterator<Key> it = userTeam.iterator(); it.hasNext();) {
				userKey = it.next();
				u = pm.getObjectById(User.class, userKey);
				if (u.getAllocationSet() != null
						&& u.getAllocationSet().contains(
								KeyFactory.createKey(Allocation.class
										.getSimpleName(), u.getLogin()
										+ project.getProjectCode()))) {
					allocationDB = pm.getObjectById(Allocation.class,
							KeyFactory.createKey(Allocation.class
									.getSimpleName(), u.getLogin()
									+ project.getProjectCode()));
				} else {
					allocationDB = new Allocation();
					allocationDB.setKey(KeyFactory.createKey(Allocation.class
							.getSimpleName(), u.getLogin()
							+ project.getProjectCode()));
				}

				allocationDB.setProjectKey(project.getKey());
				allocationDB.setProjectName(project.getProjectName());
				// allocation.setUserKey(u.getKey());
				// allocation.setUser(u);
				allocationDB.setIsActive(Boolean.TRUE);
				if (u.getAllocationSet() != null) {
					for (Iterator<Key> it1 = u.getAllocationSet().iterator(); it1
							.hasNext();) {
						allocationKey = it1.next();
						allocationInUser = pm.getObjectById(Allocation.class,
								allocationKey);
						if (!allocationKey.equals(KeyFactory.createKey(
								Allocation.class.getSimpleName(), u.getLogin()
										+ project.getProjectCode()))) {
							allocationInUser.setIsActive(Boolean.FALSE);
						}
					}
					u.getAllocationSet().add(allocationDB.getKey());
				} else {
					allocationSet = new HashSet<Key>();
					allocationSet.add(allocationDB.getKey());
					u.setAllocationSet(allocationSet);
				}
				pm.makePersistent(allocationDB);
				pm.makePersistent(u);
			}

		} finally {
			pm.close();
		}
	}

	public static void updateManagersInUsers() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Project.class);
		Query userQuery = pm.newQuery(User.class);
		List<Project> results;
		List<User> userResults;
		Project project = null;
		User manager = null;
		Set<Key> managerKeys = new HashSet<Key>();
		try {
			results = (List<Project>) query.execute();

			if (results != null && results.size() > 0) {
				for (Iterator<Project> it1 = results.iterator(); it1.hasNext();) {
					project = it1.next();
					manager = pm.getObjectById(User.class, project
							.getManagerKey());
					manager.setRole("Supervisor");
					manager.setRolekey(KeyFactory.createKey(Role.class
							.getSimpleName(), "Supervisor"));
					managerKeys.add(project.getManagerKey());
					pm.makePersistent(manager);
				}

			}

			userQuery = pm.newQuery(User.class, "role == paramRole");
			userQuery.declareParameters("java.lang.String paramRole");

			userResults = (List<User>) userQuery.execute("Supervisor");

			User supervisors = null;
			if (userResults != null && userResults.size() > 0) {
				for (Iterator<User> it1 = userResults.iterator(); it1.hasNext();) {
					supervisors = it1.next();
					if (!managerKeys.contains(supervisors.getKey())) {
						supervisors.setRole("Staff");
						supervisors.setRolekey(KeyFactory.createKey(Role.class
								.getSimpleName(), "Staff"));
						pm.makePersistent(supervisors);

					}
				}

			}

		} finally {

			pm.close();
		}

	}

	public static List<Project> getProjects() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Project.class);
		List<Project> results;
		List<Project> returnList;
		try {
			results = (List<Project>) query.execute();
			returnList = results;
			System.out.println("Success list" + returnList);
		} finally {
			pm.close();
		}
		return results;
	}

	public static List<Department> getDepartments() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Department.class);
		List<Department> results;
		List<Department> returnList;
		try {
			results = (List<Department>) query.execute();
			returnList = results;
			System.out.println("Success list" + returnList);
		} finally {
			pm.close();
		}
		return results;
	}

	public static Department getDepartment(String deptId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Department dept = null;
		try {
			Key deptKey = KeyFactory.createKey(
					Department.class.getSimpleName(), deptId);
			dept = pm.getObjectById(Department.class, deptKey);
			System.out.println("Success retrieval" + dept);
		} finally {
			pm.close();
		}
		return dept;
	}
}
