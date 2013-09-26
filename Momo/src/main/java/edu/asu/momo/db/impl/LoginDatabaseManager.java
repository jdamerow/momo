package edu.asu.momo.db.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.ILoginManager;
import edu.asu.momo.user.User;

@Service
public class LoginDatabaseManager implements ILoginManager {

	@Autowired
	private IDatabaseManager dbManager;
	private ObjectContainer database;
	
	@PostConstruct
	public synchronized void init() {
		database = dbManager.getClient();
	}
	
	@Override
	public User getUserById(String username) {
		User example = new User();
		example.setUsername(username);
		ObjectSet<User> foundUsers = database.queryByExample(example);
		if (foundUsers.size() == 0)
			return null;
					
		return foundUsers.get(0);
	}
	
}
