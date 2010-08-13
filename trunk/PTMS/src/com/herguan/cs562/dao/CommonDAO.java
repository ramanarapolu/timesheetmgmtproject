package com.herguan.cs562.dao;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;

public class CommonDAO {
	public static void deleteUser() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(User.class);
			List<User> results = (List<User>) query.execute();
			if (results.iterator().hasNext()) {

				for (User u : results) {
					pm.deletePersistent(u);
				}
			}
						
		} finally {
			pm.close();
		}
	}

	public static void deleteRole() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Role.class);
			List<Role> results = (List<Role>) query.execute();
			if (results.iterator().hasNext()) {

				for (Role u : results) {
					pm.deletePersistent(u);
				}
			}
						
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

	
	public static void createUser(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
	}

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

	public static User getUser(String username) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
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
