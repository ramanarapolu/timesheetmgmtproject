package com.herguan.cs562.dao;

import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.herguan.cs562.db.PMF;
import com.herguan.cs562.model.Department;
import com.herguan.cs562.model.Role;
import com.herguan.cs562.model.User;

public class UserDAO {

	public static List<User> getAllUsers() {
		List<User> userList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {

			Query query = pm.newQuery(User.class);
			List<User> results = (List<User>) query.execute();
			userList = results;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return userList;

	}

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
					if (u.getLogin().equalsIgnoreCase(username)
							&& u.getPassword().equals(password)) {
						user = u;
					}else{
						user = null;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;
	}
}
