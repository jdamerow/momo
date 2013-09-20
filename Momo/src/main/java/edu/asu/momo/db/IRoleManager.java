package edu.asu.momo.db;

import edu.asu.momo.core.Role;

/**
 * Interface to be implemented by role manager classes.
 * 
 * @author Julia Damerow
 *
 */
public interface IRoleManager {

	/**
	 * Get role by its id.
	 * @param id role id
	 * @return role with appropriate id
	 */
	public abstract Role getRole(String id);

	/**
	 * Get all roles.
	 * @return array of all roles
	 */
	public abstract Role[] getRoles();

}