package edu.asu.momo.db.impl.objectdb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.asu.momo.core.Role;
import edu.asu.momo.db.IDatabaseManager;
import edu.asu.momo.db.IUserDBManager;
import edu.asu.momo.db.impl.objectdb.persist.PersistantUser;
import edu.asu.momo.user.IUserFactory;
import edu.asu.momo.user.User;

@Service
public class ObjectDbUserDBManager implements IUserDBManager {

	@Autowired
	private IDatabaseManager dbmanager;

	@Autowired
	private IUserFactory userFactory;
	
	@Transactional
	public boolean saveUser(User user) {
		PersistantUser persUser = new PersistantUser();
		persUser.setEmail(user.getEmail());
		persUser.setName(user.getName());
		persUser.setPassword(userFactory.encrypt(user.getPassword()));
		persUser.setUsername(user.getUsername());
		persUser.setRoles(new ArrayList<Role>());
		for (GrantedAuthority auth : user.getAuthorities()) {
			persUser.getRoles().add(new Role(auth.getAuthority(), auth.getAuthority()));
		}
		dbmanager.store(persUser);
		return true;
	}

	@Override
	public User getUserById(String userId) {
		TypedQuery<PersistantUser> query =
			      dbmanager.getManager().createQuery("SELECT u FROM PersistantUser u WHERE u.username = :userId", PersistantUser.class);
		query.setParameter("userId", userId);
		try {
			PersistantUser persUser = query.getSingleResult();
			return userFactory.createUser(persUser.getUsername(), persUser.getName(), persUser.getEmail(), persUser.getPassword(), persUser.getRoles());
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<PersistantUser> query =
			      dbmanager.getManager().createQuery("SELECT u FROM PersistantUser u", PersistantUser.class);
		List<PersistantUser> results = query.getResultList();
		List<User> users = new ArrayList<User>();
		for (PersistantUser user : results) {
			users.add(userFactory.createUser(user.getUsername(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles()));
		}
		return users;
	}

	@Override
	@Transactional
	public boolean deleteUser(String username) {
		TypedQuery<PersistantUser> query =
			      dbmanager.getManager().createQuery("SELECT u FROM PersistantUser u WHERE u.username = :userId", PersistantUser.class);
		query.setParameter("userId", username);
		try {
			PersistantUser persUser = query.getSingleResult();
			dbmanager.delete(persUser.getId(), PersistantUser.class);
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		TypedQuery<PersistantUser> query =
			      dbmanager.getManager().createQuery("SELECT u FROM PersistantUser u WHERE u.username = :userId", PersistantUser.class);
		query.setParameter("userId", user.getUsername());
		PersistantUser persUser = query.getSingleResult();
		dbmanager.update(persUser);
		return true;
	}

}
