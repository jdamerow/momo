package edu.asu.momo.db;

import edu.asu.momo.user.User;

public interface ILoginManager {

	public abstract User getUserById(String username);

}