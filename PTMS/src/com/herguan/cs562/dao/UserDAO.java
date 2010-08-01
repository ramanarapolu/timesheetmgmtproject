package com.herguan.cs562.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;

public class UserDAO {
	public static User getUser(String username, String password) {

		User user = null;

		try {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			// String[] params = {username,password};
			Query query = pm.newQuery(User.class,
					"login == paramusername && password == parampassword");
			query
					.declareParameters("java.lang.String paramusername,java.lang.String parampassword");

			List<User> results = (List<User>) query.execute(username, password);

			if (results.iterator().hasNext() && results.size() == 1) {

				for (User u : results) {
					System.out.println("User :" + u.getLogin() + " : "
							+ u.getRoleId());
					if (u.getRoleId().toString().contains("Student")) {
						Key k = (KeyFactory.createKey(Role.class
								.getSimpleName(), "Student"));
						Role r = pm.getObjectById(Role.class, k);
						System.out.println("Role :" + r.getRole_name());
						u.setRole(r.getRole_name());
					} else if (u.getRoleId().toString().contains("Supervisor")) {
						Key k = (KeyFactory.createKey(Role.class
								.getSimpleName(), "Supervisor"));
						Role r = pm.getObjectById(Role.class, k);
						System.out.println("Role :" + r.getRole_name());
						u.setRole(r.getRole_name());
					} else if (u.getRoleId().toString().contains("Admin")) {
						Key k = (KeyFactory.createKey(Role.class
								.getSimpleName(), "Admin"));
						Role r = pm.getObjectById(Role.class, k);
						System.out.println("Role :" + r.getRole_name());
						u.setRole(r.getRole_name());
					}

					/*
					 * query = pm.newQuery(Role.class); List<Role> results1 =
					 * (List<Role>) query.execute();
					 * if(results.iterator().hasNext()){ for(Role r : results1){
					 * System.out.println(" ROle printing ; "+r.getId());
					 * System.out.println(" ROle printing ; "+r.getRole_name());
					 * } }
					 */
					/*
					 * Key k = (KeyFactory
					 * .createKey(Role.class.getSimpleName(), "Student")); Role
					 * r = pm.getObjectById(Role.class,k);
					 * System.out.println("Role :" + r.getRole_name());
					 * user.setRole(r.getRole_name());
					 */
					user = u;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;
	}
}
