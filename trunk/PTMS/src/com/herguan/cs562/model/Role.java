package com.herguan.cs562.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Role {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	private String role_name;

	public Role(String roleName) {
		super();
		role_name = roleName;
	}

	public Key getId() {
		return key;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
	}
	
	

	

}
