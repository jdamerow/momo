package edu.asu.momo.db.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.user.User;

@Service
@Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
public class Db4oDBUserManager implements IUserManager {

	@Autowired
	private IDatabaseManager dbManager;
	private ObjectContainer database;
	
	@PostConstruct
	public void init() {
		database = dbManager.getClient();
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.IUserManager#saveUser(edu.asu.momo.core.User)
	 */
	@Override
	public boolean saveUser(User user) {
		database.store(user);
		database.commit();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.db.impl.IUserManager#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String username) {
		User example = new User();
		example.setUsername(username);
		ObjectSet<User> foundUsers = database.queryByExample(example);
		if (foundUsers.size() == 0)
			return null;
					
		return foundUsers.get(0);
	}
	
	@Override
	public List<User> getAllUsers() {
		ObjectSet<User> users = database.query(User.class);
		List<User> allUsers = new ArrayList<User>();
		allUsers.addAll(users);
		return allUsers;
	}
	
	@Override
	public boolean deleteUser(String username) {
		User userToBeDeleted = getUserById(username);
		database.delete(userToBeDeleted);
		return true;
	}
	
	@PreDestroy
	public void shutdown() {
		database.close();
	}
}
