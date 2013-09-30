package edu.asu.momo.db.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.db4o.ObjectContainer;

import edu.asu.momo.db.IDatabaseManager;

public abstract class DBManager {
	
	@Autowired IDatabaseManager dbManager;
	
	ObjectContainer database;
	
	public DBManager() {
		super();
	}

	@PostConstruct
	public synchronized void init() {
		database = dbManager.getClient();
	}

	public boolean updateObject(Object object) {
		database.store(object);
		database.commit();
		return true;
	}
}