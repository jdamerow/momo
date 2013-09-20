package edu.asu.momo.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Role;
import edu.asu.momo.db.IRoleManager;

@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
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
