package edu.asu.momo.db;

import edu.asu.momo.core.Role;

public interface IRoleManager {

	public abstract Role getRole(String id);

	public abstract Role[] getRoles();

}