package com.herguan.cs562.dao;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Food;
import com.herguan.cs562.model.Person;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.Staff;
import com.herguan.cs562.model.Student;
import com.herguan.cs562.model.User;

public class CommonDAO {


	public static void updateUser(Staff staff) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(User.class,"login == 'Staff'");
		
		try {
			List<User> results = (List<User>) query.execute();
			if (results.iterator().hasNext()) {

				for (User u : results) {
					System.out.println("User updated: " + u.getLogin());
					u.setStudentKey(staff.getKey());
					System.out.println("User updated: " + u.getStaffKey());

				}
			}
			
			
		} finally {
			pm.close();
		}
	}
	
	public static void updateUser(Student student) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(User.class,"login == 'Student'");
		
		try {
			List<User> results = (List<User>) query.execute();
			if (results.iterator().hasNext()) {

				for (User u : results) {
					System.out.println("User updated: " + u.getLogin());
					u.setStudentKey(student.getKey());
					System.out.println("User updated: " + u.getStudentKey());

				}
			}
			
			
		} finally {
			pm.close();
		}
	}
	
	public static void createStudent(Student student) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(student);
		} finally {
			pm.close();
		}
	}

	public static void createStaff(Staff staff) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(staff);
		} finally {
			pm.close();
		}
	}

	
	public static void createRole(Role role) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(role);
		} finally {
			pm.close();
		}
	}

	public static void createFood(Food food) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(food);
		} finally {
			pm.close();
		}
	}
	
	public static void getFood(String personName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Key pkey = (KeyFactory.createKey(Person.class.getSimpleName(), "John"));
			Person p = pm.getObjectById(Person.class,pkey);
			System.out.println("Person details :"+p.getName()+" : "+p.getFavoriteFood());
			
			Key fkey = p.getFavoriteFood();
			Food f = pm.getObjectById(Food.class, fkey);
			System.out.println("Food details :"+f.getFoodName());
			
			//pm.makePersistent(food);
		} finally {
			pm.close();
		}
	}
	public static void createPerson(Person person) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(person);
		} finally {
			pm.close();
		}
	}
	public static void createUser(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}

	/*
	 * public static void updateRole(){ PersistenceManager pm =
	 * PMF.get().getPersistenceManager();
	 * 
	 * Query query = pm.newQuery(Role.class); try { List<Role> results =
	 * (List<Role>) query.execute();
	 * System.out.println("size : "+results.size()); if
	 * (results.iterator().hasNext()) { for (Role r : results) {
	 * System.out.println("ROle : "+r.getRole_name());
	 * System.out.println("ROle : "+r.getId()+" : "+ r.getId().equals("0"));
	 * if(r.getId().equals(new Long("0"))){ r.setId("101");
	 * pm.makePersistent(r);
	 * 
	 * 
	 * } //pm.deletePersistent(r); } } } finally { pm.close(); } getRole(); }
	 * 
	 * public static void deleteRole(){ PersistenceManager pm =
	 * PMF.get().getPersistenceManager();
	 * 
	 * Query query = pm.newQuery(Role.class); try { List<Role> results =
	 * (List<Role>) query.execute();
	 * System.out.println("size : "+results.size());
	 * pm.deletePersistentAll(results); } finally { pm.close(); } }
	 */
	@SuppressWarnings("unchecked")
	public static void getRole() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Role.class);

		try {
			List<Role> results = (List<Role>) query.execute();
			System.out.println("size : " + results.size());
			if (results.iterator().hasNext()) {
				for (Role r : results) {
					System.out.println("ROle : " + r.getRole_name());
					System.out.println("ROle : " + r.getId());
					// pm.deletePersistent(r);
				}
			} else {
				// ... no results ...
			}
		} finally {
			query.closeAll();
		}
	}

	public static User getUser() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		test();
		Query query = pm.newQuery(User.class);
		User user = null;

		try {
			List<User> results = (List<User>) query.execute();
			if (results.iterator().hasNext()) {

				for (User u : results) {
					System.out.println("User : " + u.getLogin());
					user = u;

				}
			} else {
				// ... no results ...
			}
		} finally {
			query.closeAll();
		}
		return user;
	}

	public static void getStaff() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Staff.class);

		try {
			List<Staff> results = (List<Staff>) query.execute();
			if (results.iterator().hasNext()) {

				for (Staff s : results) {
					//System.out.println("staff : " + s.getLogin());
				}
			} else {
				// ... no results ...
			}
		} finally {
			query.closeAll();
		}
	}

	public static void getStudent() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Student.class);

		try {
			List<Student> results = (List<Student>) query.execute();
			if (results.iterator().hasNext()) {

				for (Student s : results) {
					//System.out.println("student : " + s.getLogin());

				}
			} else {
				// ... no results ...
			}
		} finally {
			query.closeAll();
		}
	}

	public static void test() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Extent e = pm.getExtent(User.class, true);
		Query q = pm.newQuery(e);
		List<User> results = (List<User>) q.execute();
		if (results.iterator().hasNext()) {
			for (User u : results) {
				System.out.println("test : " + u.getLogin());
			}
		}
	}

	public static User getUser(String username) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		test();
		Query query = pm.newQuery(User.class,"user == 'Smith'");
		User user = null;

		try {
			List<User> results = (List<User>) query.execute();
			if (results.iterator().hasNext()) {

				for (User u : results) {
					System.out.println("User : " + u.getLogin());
					user = u;

				}
			} else {
				// ... no results ...
			}
		} finally {
			query.closeAll();
		}
		return user;
	}
}
