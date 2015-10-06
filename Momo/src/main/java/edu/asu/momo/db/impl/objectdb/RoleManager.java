package edu.asu.momo.db.impl.objectdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Role;
import edu.asu.momo.db.IRoleManager;

@Service
public class RoleManager implements IRoleManager {

	@Autowired
	private List<Role> roles;

	@Override
	public Role[] getRoles() {
		return roles.toArray(new Role[roles.size()]);
	}
	
	@Override
	public Role getRole(String id) {
		for (Role role : roles) {
			if (role.getId().equals(id))
				return role;
		}
		return null;
	}
	
}
